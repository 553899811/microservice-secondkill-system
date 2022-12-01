package com.newbiegroup.secondkill.service.impl;

import com.newbiegroup.secondkill.common.enums.YnEnum;
import com.newbiegroup.secondkill.common.utils.GenerateNoUtil;
import com.newbiegroup.secondkill.entity.StockFlowLog;
import com.newbiegroup.secondkill.enums.StkOperTypeEnum;
import com.newbiegroup.secondkill.enums.StockFlowLogStatusEnum;
import com.newbiegroup.secondkill.manager.StockFlowLogManager;
import com.newbiegroup.secondkill.service.StockFlowLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/13 20:21
 */
@Slf4j
@Service
public class StockFlowLogServiceImpl implements StockFlowLogService {

    @Autowired
    private StockFlowLogManager stockFlowLogManager;

    /**
     * 初始化库存流水
     * @param wareItemId
     * @param quantity
     * @param stkOptType
     * @return
     */
    @Override
    public Long initStockFlowLog(Long wareItemId, Integer quantity, Integer stkOptType) {
        StockFlowLog entity = new StockFlowLog();
        entity.setWareItemId(wareItemId);
        entity.setQuantity(quantity);
        entity.setStkOptType(StkOperTypeEnum.toEnum(stkOptType).getCode());
        entity.setStockFlowLogId(GenerateNoUtil.generateUserId());
        entity.setStatus(StockFlowLogStatusEnum.INIT.getCode());
        entity.setYn(YnEnum.VALID.code());
        stockFlowLogManager.save(entity);
        return entity.getStockFlowLogId();
    }
}
