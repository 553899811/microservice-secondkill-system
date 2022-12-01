package com.newbiegroup.secondkill.manager;

import com.newbiegroup.secondkill.entity.WareItemStock;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 0:59
 */
public interface WareItemStockManager {

    boolean asyncDecreaseItemStock(Long wareItemId, Integer amount);

    void decreaseItemStock(Long wareItemId, Integer decrement);
    void increaseItemStock(Long wareItemId,Integer increment);
}
