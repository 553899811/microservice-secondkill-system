package com.newbiegroup.secondkill.manager;

import com.newbiegroup.secondkill.entity.StockFlowLog;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/13 20:12
 */
public interface StockFlowLogManager {
    boolean save(StockFlowLog entity);

    StockFlowLog selectByStockFlowLogId(Long stockFlowLogId);

    boolean modify(StockFlowLog entity);
}
