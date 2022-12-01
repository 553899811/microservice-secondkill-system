package com.newbiegroup.secondkill.listeners.transaction;

import com.alibaba.fastjson.JSON;
import com.newbiegroup.secondkill.dto.WareItemStockModifyDTO;
import com.newbiegroup.secondkill.entity.StockFlowLog;
import com.newbiegroup.secondkill.enums.StockFlowLogStatusEnum;
import com.newbiegroup.secondkill.manager.OrderInfoManager;
import com.newbiegroup.secondkill.manager.StockFlowLogManager;
import com.newbiegroup.secondkill.message.rocketmq.RocketMqMessage;
import com.newbiegroup.secondkill.param.OrderCreateParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/10 11:23
 */
@Slf4j
@RocketMQTransactionListener
public class WareItemStockModifyTransactionListener implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderInfoManager orderInfoManager;
    @Autowired
    private StockFlowLogManager stockFlowLogManager;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        OrderCreateParam param = (OrderCreateParam) arg;
        try {
            Object payload = msg.getPayload();
            log.info("payload:{}", JSON.toJSONString(payload));
            orderInfoManager.createOrder(param);
        } catch (Exception e) {
            e.printStackTrace();
            Long stockFlowLogId = param.getStockFlowLogId();
            StockFlowLog stockFlowLog = stockFlowLogManager.selectByStockFlowLogId(stockFlowLogId);
            stockFlowLog.setStatus(StockFlowLogStatusEnum.ROLL_BACK.getCode());
            stockFlowLogManager.modify(stockFlowLog);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info("WareItemStockModifyTransactionListener checkLocalTransaction,msg:{}", JSON.toJSONString(msg));
        RocketMqMessage rocketMqMessage = (RocketMqMessage) msg.getPayload();
        String content = rocketMqMessage.getContent();
        WareItemStockModifyDTO modifyDTO = JSON.parseObject(content, WareItemStockModifyDTO.class);
        StockFlowLog stockFlowLog = stockFlowLogManager.selectByStockFlowLogId(modifyDTO.getStockFlowLogId());
        if (stockFlowLog == null) {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
        if (StockFlowLogStatusEnum.INVENTORY_DEDUCTION.getCode() == stockFlowLog.getStatus()) {
            return RocketMQLocalTransactionState.COMMIT;
        } else if (StockFlowLogStatusEnum.INIT.getCode() == stockFlowLog.getStatus()) {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
