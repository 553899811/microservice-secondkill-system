package com.newbiegroup.secondkill.manager.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.newbiegroup.secondkill.common.rateLimit.GuavaRateLimit;
import com.newbiegroup.secondkill.common.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2022/1/10 17:08
 */
@Slf4j
@Aspect
@Component
public class GuavaRateLimitAspect {

    private RateLimiter rateLimiter = RateLimiter.create(Double.MAX_VALUE);

    @ResponseBody
    @Around(value = "@annotation(com.newbiegroup.secondkill.common.rateLimit.GuavaRateLimit)")
    public Object arountNotice(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //获取目标方法
        Method targetMethod = methodSignature.getMethod();
        if (targetMethod.isAnnotationPresent(GuavaRateLimit.class)) {
            //获取目标方法的@GavaRateLimit 注解
            GuavaRateLimit rateLimit = targetMethod.getAnnotation(GuavaRateLimit.class);
            rateLimiter.setRate(rateLimit.perSecond());
            if (!rateLimiter.tryAcquire(rateLimit.timeOut(), rateLimit.timeOutUnit())) {
                return WebResponse.failed("活动太火爆了，请稍后再试！");
            }
        }
        return pjp.proceed();
    }
}
