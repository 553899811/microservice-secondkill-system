package com.newbiegroup.secondkill.common.rateLimit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2022/1/10 16:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GuavaRateLimit {

    /**
     * @return
     */
    String value() default "";

    /**
     * 每秒向同种放入令牌的数量
     * 默认最大　即为不做限流处理
     *
     * @return
     */
    double perSecond() default Double.MAX_VALUE;

    /**
     * 获取令牌的等待时间
     * 默认　0
     * @return
     */
    int timeOut() default 0;

    /**　
     * 超时时间
     * @return
     */
    TimeUnit timeOutUnit() default TimeUnit.MILLISECONDS;
}
