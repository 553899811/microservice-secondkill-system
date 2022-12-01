package com.newbiegroup.secondkill.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/7 16:31
 */
@Data
public class WareItemStockModifyDTO implements Serializable {
    private static final long serialVersionUID = 7870256701500551008L;
    private Long wareItemId;
    private Integer amount;
    private int stockOptType;
    private Long stockFlowLogId;
}
