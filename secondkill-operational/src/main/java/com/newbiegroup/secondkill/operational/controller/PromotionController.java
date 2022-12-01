package com.newbiegroup.secondkill.operational.controller;

import com.newbiegroup.secondkill.common.response.WebResponse;
import com.newbiegroup.secondkill.operational.service.PromotionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/2 13:25
 */
@RestController
@RequestMapping(value = "/operational/promo")
public class PromotionController extends HealthController {

    @Autowired
    private PromotionInfoService promotionInfoService;

    @PostMapping(value = "/publish")
    public WebResponse publishPromotion(@RequestParam(name = "id") Long id) {
        promotionInfoService.publishPromotion(id);
        return WebResponse.success();
    }
}
