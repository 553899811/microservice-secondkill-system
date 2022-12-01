package com.newbiegroup.secondkill.manager;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/7 16:22
 */
public interface WareItemStockMqOptionManager<T> {

    void onMsg(T t);

    void onMsg(String jsonStr);
}
