package com.newbiegroup.secondkill.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbiegroup.secondkill.dao.StockFlowLogMapper;
import com.newbiegroup.secondkill.entity.StockFlowLog;
import com.newbiegroup.secondkill.manager.StockFlowLogManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/13 20:13
 */
@Slf4j
@Service
public class StockFlowLogManagerImpl extends ServiceImpl<StockFlowLogMapper, StockFlowLog> implements StockFlowLogManager {

    @Autowired
    private StockFlowLogMapper stockFlowLogMapper;

    @Override
    public boolean save(StockFlowLog entity) {
        return super.save(entity);
    }

    @Override
    public StockFlowLog selectByStockFlowLogId(Long stockFlowLogId) {
        return stockFlowLogMapper.selectByFlowLogId(stockFlowLogId);
    }

    @Override
    public boolean modify(StockFlowLog entity) {
        return super.updateById(entity);
    }
}
