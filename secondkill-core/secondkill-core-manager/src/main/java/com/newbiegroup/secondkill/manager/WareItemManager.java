package com.newbiegroup.secondkill.manager;

import com.newbiegroup.secondkill.entity.WareItem;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/1 18:57
 */
public interface WareItemManager {

    boolean decreaseStock(Long wareItemId,Integer amount);

    boolean increaseStock(Long wareItemId,Integer amount);
}
