package com.newbiegroup.secondkill.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbiegroup.secondkill.dao.OrderItemMappper;
import com.newbiegroup.secondkill.entity.OrderItem;
import com.newbiegroup.secondkill.manager.OrderItemManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 18:31
 */
@Slf4j
@Service
public class OrderItemManagerImpl extends ServiceImpl<OrderItemMappper, OrderItem> implements OrderItemManager {

}
