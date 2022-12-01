package com.newbiegroup.secondkill.common.utils;

import org.springframework.stereotype.Component;

/**
 * <p>ClassName: 要求做到动态控制 生产userId和orderId  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/7/10 23:09
 */
@Component
public class GenerateNoUtil {
    
    private static SnowflakeIdWorker idWorker = new SnowflakeIdWorker(2, 5);

    private static final long PREFIX_REMAINDER = 1000000000000L;
    private static final long SUFFIX_REMAINDER = 10000000L;

    public static Long generateUserId() {
        long nextId = idWorker.nextId();
        long prefixId = nextId / PREFIX_REMAINDER;
        long suffixId = nextId % SUFFIX_REMAINDER;
        return prefixId * SUFFIX_REMAINDER + suffixId;
    }

    /**
     * 生产订单ID
     * 因考虑到后期分库分表，将userId和orderId 做一定程度的关联;
     *
     * @param userId
     * @return
     */
    public static Long generateOrderId(Long userId) {
        Long suffixOrderId = userId % 100;
        return idWorker.nextId() / 100 + suffixOrderId;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Long userId = generateUserId();
            System.out.println("userId:"+userId);
            Long orderId = generateOrderId(userId);
            System.out.println("orderId:"+orderId);
        }
    }
}
