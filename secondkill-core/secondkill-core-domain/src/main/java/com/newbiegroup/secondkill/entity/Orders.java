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
 * @date 2021/8/2 18:06
 */
@Data
public class Orders extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -8505423027838880539L;

    private Long orderId;

    private Long userId;

    private Long orderPrice;

    private Integer promotionId;

}
