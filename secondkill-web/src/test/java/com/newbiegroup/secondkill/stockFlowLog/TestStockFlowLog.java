package com.newbiegroup.secondkill.stockFlowLog;

import com.newbiegroup.secondkill.SpringBaseTest;
import com.newbiegroup.secondkill.dao.StockFlowLogMapper;
import com.newbiegroup.secondkill.entity.StockFlowLog;
import com.newbiegroup.secondkill.service.StockFlowLogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/14 20:02
 */
public class TestStockFlowLog extends SpringBaseTest {

    @Autowired
    private StockFlowLogService stockFlowLogService;

    @Autowired
    private StockFlowLogMapper stockFlowLogMapper;

    @Test
    public void testInitStockFlowLog() {
        stockFlowLogService.initStockFlowLog(1L, 1, 0);
    }

    @Test
    public void testSelectStockFlow() {
        Long flowLogId = 122L;
        StockFlowLog stockFlowLog = stockFlowLogMapper.selectByFlowLogId(flowLogId);
        System.out.println(stockFlowLog.getStockFlowLogId());
    }
}
