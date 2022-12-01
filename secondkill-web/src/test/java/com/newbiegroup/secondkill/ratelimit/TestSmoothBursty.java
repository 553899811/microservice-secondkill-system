package com.newbiegroup.secondkill.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

/**
 * <p>ClassName: 平滑突发限流 </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2022/1/10 15:27
 */
public class TestSmoothBursty {

    /**
     * 使用 RateLimiter的静态方法创建一个限流器，设置每秒放置的令牌数为5个。
     * 返回的RateLimiter对象可以保证1秒内不会给超过5个令牌，并且以固定速率进行放置，达到平滑输出的效果。
     */
    @Test
    public void test() {
        RateLimiter r = RateLimiter.create(5);
        while (true) {
            System.out.println("get one token: " + r.acquire() + " s");
        }
    }

    /**
     * RateLimiter使用令牌桶算法，会进行令牌的累积，如果获取令牌的频率比较低，则不会导致等待，直接获取令牌。
     */
    @Test
    public void test1() {
        RateLimiter r = RateLimiter.create(2);
        while (true) {
            System.out.println("get one token: " + r.acquire(1) + " s");
            try {
                Thread.sleep(2000L);
            } catch (Exception e) {
            }
            System.out.println("get one token: " + r.acquire(1) + " s");
            System.out.println("get one token: " + r.acquire(1) + " s");
            System.out.println("get one token: " + r.acquire(1) + " s");
            System.out.println("end");
        }
    }

    /**
     * RateLimiter由于会累积令牌，所以可以应对突发流量。
     * 在下面代码中，有一个请求会直接请求5个令牌，但是由于此时令牌桶中有累积的令牌，足以快速响应。
     * RateLimiter在没有足够令牌发放时，采用滞后处理的方式，也就是前一个请求获取令牌所需等待的时间由下一次请求来承受，
     * 也就是代替前一个请求进行等待。
     */
    @Test
    public void test2() {
        RateLimiter r = RateLimiter.create(0.5);
        while (true) {
            System.out.println("get 5 token: " + r.acquire(5) + " s");
            System.out.println("get one token: " + r.acquire(1) + " s");
            System.out.println("get one token: " + r.acquire(1) + " s");
            System.out.println("get one token: " + r.acquire(1) + " s");
            System.out.println("end");
        }
    }
}
