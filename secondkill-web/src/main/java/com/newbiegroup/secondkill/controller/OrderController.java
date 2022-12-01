package com.newbiegroup.secondkill.controller;

import com.newbiegroup.secondkill.common.response.WebResponse;
import com.newbiegroup.secondkill.param.OrderCreateParam;
import com.newbiegroup.secondkill.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/5/19 13:44
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    OrderInfoService orderService;

    @RequestMapping("/create")
    public WebResponse create(@RequestBody OrderCreateParam param) {
        orderService.createOrder(param);
        return WebResponse.success();
    }
}
