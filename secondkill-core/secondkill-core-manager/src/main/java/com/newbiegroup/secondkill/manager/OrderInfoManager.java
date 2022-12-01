package com.newbiegroup.secondkill.manager;

import com.newbiegroup.secondkill.entity.Orders;
import com.newbiegroup.secondkill.param.OrderCreateParam;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 18:30
 */
public interface OrderInfoManager {
    Orders createOrder(OrderCreateParam param);

    boolean transactionAsyncOrderCreateReduceStock(OrderCreateParam param);
}
