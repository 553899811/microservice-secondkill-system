package com.newbiegroup.secondkill.manager.impl;

import com.alibaba.fastjson.JSON;
import com.newbiegroup.secondkill.dto.WareItemStockModifyDTO;
import com.newbiegroup.secondkill.enums.StkOperTypeEnum;
import com.newbiegroup.secondkill.manager.WareItemStockMqOptionManager;
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
 * @date 2021/12/7 16:24
 */
@Slf4j
@Service
public class WareItemStockModifyManagerImpl implements WareItemStockMqOptionManager<WareItemStockModifyDTO> {

    @Autowired
    private WareItemStockManagerImpl wareItemStockManager;

    @Override
    public void onMsg(WareItemStockModifyDTO t) {
        if (t == null) {
            log.error("wareItemStockModifyManagerServiceImpl,consume message couldn't be empty");
            return;
        }
        log.info("deal ware_item_stock,wareItemId:{},amount:{},opt:{}", t.getWareItemId(), t.getAmount(), t.getStockOptType());
        Long wareItemId = t.getWareItemId();
        Integer amount = t.getAmount();
        StkOperTypeEnum stkOperTypeEnum = StkOperTypeEnum.toEnum(t.getStockOptType());
        if (stkOperTypeEnum == null) {
            return;
        }
        switch (stkOperTypeEnum) {
            case STOCK_DEDUCTION:
                wareItemStockManager.decreaseItemStock(wareItemId, amount);
                break;
            case STOCK_INCREASE:
                wareItemStockManager.increaseItemStock(wareItemId, amount);
                break;
            default:
                break;
        }
    }

    @Override
    public void onMsg(String jsonStr) {
        onMsg(getMsgObj(jsonStr));
    }

    private WareItemStockModifyDTO getMsgObj(String jsonStr) {
        return JSON.parseObject(jsonStr, WareItemStockModifyDTO.class);
    }
}
