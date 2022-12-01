package com.newbiegroup.secondkill.common.utils;

import java.math.BigDecimal;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 10:35
 */
public class BigDecimalUtils {

    /**
     * 货币精度分
     */
    public static final Double CURRENCY_CENTS_PRECISION_D = 100D;

    public static BigDecimal amountLongToDecimal(Long amount) {
        if (amount == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(amount)
                .divide(new BigDecimal(CURRENCY_CENTS_PRECISION_D), 2, BigDecimal.ROUND_HALF_UP);
    }

    public static Long amountDecimalToLong(BigDecimal amount) {
        if (amount == null) {
            return 0L;
        }
        return amount.multiply(new BigDecimal(CURRENCY_CENTS_PRECISION_D)).longValue();
    }
}
