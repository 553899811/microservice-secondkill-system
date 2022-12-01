package com.newbiegroup.secondkill.param;

import lombok.Data;

import java.util.List;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/3 9:20
 */
@Data
public class OrderCreateParam extends BaseParam {
    /**
     * 促销ID
     */
    Long promotionId;
    /**
     * 秒杀数量
     */
    private Integer quantity;
    /**
     * 商品明细ID;
     */
    private Long wareItemId;

    /**
     * 库存流水ID
     */
    private Long stockFlowLogId;

}
