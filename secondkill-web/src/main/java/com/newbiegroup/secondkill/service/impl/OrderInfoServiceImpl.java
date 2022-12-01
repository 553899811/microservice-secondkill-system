package com.newbiegroup.secondkill.service.impl;

import com.newbiegroup.secondkill.common.constants.CacheConstants;
import com.newbiegroup.secondkill.common.exception.BusinessException;
import com.newbiegroup.secondkill.enums.StkOperTypeEnum;
import com.newbiegroup.secondkill.manager.CacheManager;
import com.newbiegroup.secondkill.manager.impl.OrderInfoManagerImpl;
import com.newbiegroup.secondkill.param.OrderCreateParam;
import com.newbiegroup.secondkill.service.OrderInfoService;
import com.newbiegroup.secondkill.service.StockFlowLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/3 22:47
 */
@Slf4j
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoManagerImpl orderInfoManager;
    @Autowired
    private StockFlowLogService stockFlowLogService;

    @Autowired
    private CacheManager redisTemplate;

    @Override
    public boolean createOrder(OrderCreateParam param) {
        Long wareItemId = param.getWareItemId();
        Integer quantity = param.getQuantity();
        if (redisTemplate.hasKey(String.format(CacheConstants.WARE_ITEM_STOCK_INVALID, wareItemId))) {
            throw new BusinessException("库存不足");
        }
        Long stockFlowLogId = stockFlowLogService
                .initStockFlowLog(wareItemId, quantity, StkOperTypeEnum.STOCK_DEDUCTION.getCode());
        param.setStockFlowLogId(stockFlowLogId);
        if (!orderInfoManager.transactionAsyncOrderCreateReduceStock(param)) {
            throw new BusinessException("下单失败");
        }
        return true;
    }
}
