package com.newbiegroup.secondkill.vo;

import com.newbiegroup.secondkill.common.utils.BigDecimalUtils;
import com.newbiegroup.secondkill.entity.Orders;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/3 9:18
 */
@Data
public class OrderVO implements Serializable {

    private static final long serialVersionUID = 3974317133781748111L;

    private Long orderId;

    private Long userId;

    private Long orderPrice;

    private String orderPriceYuan;

    private Integer promotionId;

    public String getOrderPriceYuan() {
        if (orderPrice == null) {
            orderPrice = 0L;
        }
        return BigDecimalUtils.amountLongToDecimal(this.getOrderPrice()).toString();
    }

    public static OrderVO getInstance(Orders entity) {
        if (null == entity) {
            return null;
        }
        OrderVO target = new OrderVO();
        BeanUtils.copyProperties(entity, target, new String[]{});
        return target;
    }
}
