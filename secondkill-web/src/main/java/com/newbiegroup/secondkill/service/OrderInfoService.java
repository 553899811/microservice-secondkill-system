package com.newbiegroup.secondkill.service;


import com.newbiegroup.secondkill.param.OrderCreateParam;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/3 9:18
 */
public interface OrderInfoService {
     boolean createOrder(OrderCreateParam param);
}
