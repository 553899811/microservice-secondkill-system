package com.newbiegroup.secondkill.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 18:23
 */
@Data
public class OrderItem extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 810242293616449782L;

    private Long id;

    private Long orderId;

    /**
     * 买赠商品存在 买一赠一
     * 同一个订单 相同SKU ，但是orderItemId 均不同 区分此类情况;
     */
    private Long orderItemId;

    /**
     * 商品明细ID
     */
    private Long wareItemId;

    /**
     * 商品sku ID
     */
    private Long skuId;

    /**
     * 该 SKU 商品单价;
     */
    private Long orderItemPrice;

    /**
     * 商品数量
     */
    private Integer quantity;
}
