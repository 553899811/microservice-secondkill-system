package com.newbiegroup.secondkill.controller;

import com.newbiegroup.secondkill.common.rateLimit.GuavaRateLimit;
import com.newbiegroup.secondkill.common.response.WebResponse;
import com.newbiegroup.secondkill.manager.CacheManager;
import com.newbiegroup.secondkill.param.WareItemCreateParam;
import com.newbiegroup.secondkill.service.WareItemService;
import com.newbiegroup.secondkill.vo.WareItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/1 17:23
 */
@RestController
@RequestMapping(value = "/ware/item")
public class WareItemController extends BaseController {

    @Autowired
    private WareItemService wareItemService;
    @Autowired
    CacheManager cacheUtils;

    @PostMapping(value = "create")
    public WebResponse create(@RequestBody WareItemCreateParam param) {
        WareItemVO wareItemVO = new WareItemVO();
        BeanUtils.copyProperties(param, wareItemVO);
        wareItemService.createWareItem(wareItemVO);
        return WebResponse.success();
    }

    @RequestMapping(value = "/list")
    public WebResponse list() {
        return WebResponse.success(wareItemService.list());
    }

    /**
     * 使用RateLimit 实现限流
     * @param wareItemId
     * @return
     */
    @GetMapping(value = "detail")
    @GuavaRateLimit(perSecond = 2, timeOut = 1)
    public WebResponse detail(@RequestParam(name = "id") Long wareItemId) {
        return WebResponse.success(wareItemService.getWareItemById(wareItemId));
    }
}
