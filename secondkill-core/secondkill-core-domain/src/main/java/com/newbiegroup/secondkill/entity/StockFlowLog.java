package com.newbiegroup.secondkill.entity;

import lombok.Data;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/13 19:55
 */
@Data
public class StockFlowLog extends BaseEntity {

    private Long stockFlowLogId;

    private Long wareItemId;

    private Integer quantity;

    private Integer stkOptType;

    private Integer status;
}
