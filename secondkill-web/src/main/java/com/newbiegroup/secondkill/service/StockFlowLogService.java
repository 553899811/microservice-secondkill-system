package com.newbiegroup.secondkill.service;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/13 20:21
 */
public interface StockFlowLogService {
    /**
     * 初始化库存流水
     * @param wareItemId
     * @param quantity
     * @param stkOptType
     * @return
     */
    Long initStockFlowLog(Long wareItemId, Integer quantity, Integer stkOptType);
}
