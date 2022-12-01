package com.newbiegroup.secondkill.manager.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbiegroup.secondkill.common.constants.RocketMqTopicTagConstants;
import com.newbiegroup.secondkill.common.enums.YnEnum;
import com.newbiegroup.secondkill.common.exception.BusinessException;
import com.newbiegroup.secondkill.common.utils.GenerateNoUtil;
import com.newbiegroup.secondkill.dao.OrdersMapper;
import com.newbiegroup.secondkill.dto.WareItemStockModifyDTO;
import com.newbiegroup.secondkill.entity.*;
import com.newbiegroup.secondkill.enums.PromotionStatusEnum;
import com.newbiegroup.secondkill.enums.StkOperTypeEnum;
import com.newbiegroup.secondkill.enums.StockFlowLogStatusEnum;
import com.newbiegroup.secondkill.manager.OrderInfoManager;
import com.newbiegroup.secondkill.manager.StockFlowLogManager;
import com.newbiegroup.secondkill.manager.UserInfoManager;
import com.newbiegroup.secondkill.manager.mq.RocketMqProducer;
import com.newbiegroup.secondkill.param.OrderCreateParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 18:30
 */
@Slf4j
@Service
public class OrderInfoManagerImpl extends ServiceImpl<OrdersMapper, Orders> implements OrderInfoManager {
    @Autowired
    private WareItemManagerImpl wareItemManager;
    @Autowired
    private UserInfoManager userInfoManager;
    @Autowired
    private PromotionInfoManagerImpl promotionInfoManager;
    @Autowired
    private OrderItemManagerImpl orderItemManager;
    @Autowired
    private RocketMqProducer rocketMqProducer;
    @Autowired
    private StockFlowLogManager stockFlowLogManager;

    @Value(value = "${rocketmq.producer.group:}")
    private String group;
    @Value(value = "${rocketmq.producer.topic:}")
    private String topic;

    @Override
    @Transactional
    public Orders createOrder(OrderCreateParam param) {
        Long wareItemId = param.getWareItemId();
        Long userId = param.getUserId();
        Long promotionId = param.getPromotionId();
        Integer quantity = param.getQuantity();
        Long stockFlowLogId = param.getStockFlowLogId();
        log.info("createOrder userId:{},wareItemId:{},order param:{}", userId, wareItemId, JSON.toJSONString(param));
        WareItem wareItem = wareItemManager.selectByWareItemId(wareItemId);
        if (wareItem == null) {
            throw new BusinessException("商品信息不存在");
        }
        UserInfo user = userInfoManager.getUserByUserId(userId);
        if (user == null) {
            throw new BusinessException("用户信息不存在");
        }
        if (quantity == null || quantity <= 0 || quantity >= 99) {
            throw new BusinessException("商品数量不合法");
        }
        PromotionInfo promotion = promotionInfoManager.selectByWareItemId(wareItemId);
        if (promotion != null) {
            if (promotionId.longValue() != promotion.getId().longValue()) {
                throw new BusinessException("活动信息不正确");
            } else if (promotion.getPromotionStatus() != PromotionStatusEnum.PROCESSING_ON.getCode()) {
                throw new BusinessException("活动信息还未开始");
            }
        }
        boolean result = wareItemManager.decreaseStock(wareItemId, quantity);
        if (!result) {
            throw new BusinessException("下单扣减缓存库存异常");
        }

        /**
         * 2. 订单入库
         *    订单明细落库
         */
        Orders os = new Orders();
        os.setUserId(userId);
        if (promotion != null) {
            Long promotionItemPrice = promotion.getPromotionItemPrice();
            os.setOrderPrice(promotionItemPrice * quantity);
        } else {
            Long wareItemPrice = wareItem.getPrice();
            os.setOrderPrice(wareItemPrice * quantity);
        }
        Long orderId = GenerateNoUtil.generateOrderId(userId);
        os.setOrderId(orderId);
        log.info("userId:{},create order,orderId:{}", userId, orderId);
        //订单主数据落库
        os.setCreated(new Date());
        os.setYn(YnEnum.VALID.code());
        os.setModified(new Date());
        this.save(os);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(os.getOrderId());
        orderItem.setOrderItemId(os.getOrderId() % 10000000);
        orderItem.setQuantity(quantity);
        orderItem.setWareItemId(wareItemId);
        orderItem.setSkuId(wareItem.getSkuId());
        orderItem.setCreated(new Date());
        orderItem.setModified(new Date());
        orderItem.setYn(YnEnum.VALID.code());
        if (promotion != null) {
            Long promotionItemPrice = promotion.getPromotionItemPrice();
            orderItem.setOrderItemPrice(promotionItemPrice);
        } else {
            Long wareItemPrice = wareItem.getPrice();
            orderItem.setOrderItemPrice(wareItemPrice);
        }
        orderItemManager.save(orderItem);
        // 商品销量
        wareItemManager.increaseSales(wareItem, quantity);


        /**
         * 设置此单的库存流水
         */
        StockFlowLog stockFlowLog = stockFlowLogManager.selectByStockFlowLogId(stockFlowLogId);
        if (stockFlowLog == null) {
            throw new BusinessException("未知错误");
        }
        stockFlowLog.setStatus(StockFlowLogStatusEnum.INVENTORY_DEDUCTION.getCode());
        stockFlowLogManager.modify(stockFlowLog);

        try {
            Thread.sleep(15000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return os;
    }

    /**
     * 事务型创建订单及同步库存扣减
     *
     * @param userId
     * @param wareItemId
     * @param promotionId
     * @param quantity
     * @return
     */
    @Override
    public boolean transactionAsyncOrderCreateReduceStock(OrderCreateParam param) {
        WareItemStockModifyDTO dto = new WareItemStockModifyDTO();
        Long wareItemId = param.getWareItemId();
        Integer quantity = param.getQuantity();
        Long stockFlowLogId = param.getStockFlowLogId();
        dto.setWareItemId(wareItemId);
        dto.setAmount(quantity);
        dto.setStockFlowLogId(stockFlowLogId);
        dto.setStockOptType(StkOperTypeEnum.STOCK_DEDUCTION.getCode());

        TransactionSendResult sendResult = null;
        try {
            sendResult = rocketMqProducer.sendMessageInTransaction(dto, topic, group, RocketMqTopicTagConstants.CREATE_ORDER_STOCK_MODIFY, param);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (LocalTransactionState.ROLLBACK_MESSAGE == sendResult.getLocalTransactionState()) {
            return false;
        } else if (LocalTransactionState.COMMIT_MESSAGE == sendResult.getLocalTransactionState()) {
            return true;
        }
        return false;
    }
}
