package com.newbiegroup.secondkill.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/23 17:57
 */
@RestController
@RequestMapping
public class HealthController {
    @GetMapping("/health")
    public String health() {
        return "this app is health";
    }

    @GetMapping("/ready")
    public String ready() {
        return "this app is ready";
    }
}
