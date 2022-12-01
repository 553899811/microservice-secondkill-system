package com.newbiegroup.secondkill.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/3 9:23
 */
@Data
public class OrderItemVO implements Serializable {
    private static final long serialVersionUID = 4654582102398359767L;
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
     * 该SKU 商品单价;
     */
    private Long orderItemPrice;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品促销ID
     * 促销分摊
     * 举例：买一送一
     *
     */
    private Long promotionId;
}
