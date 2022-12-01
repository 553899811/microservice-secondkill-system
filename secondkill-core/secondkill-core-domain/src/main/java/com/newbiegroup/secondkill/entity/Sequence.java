package com.newbiegroup.secondkill.entity;

import lombok.Data;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/10/18 18:09
 */
@Data
public class Sequence extends BaseEntity {
    private Long id;
    private String name;
    private Long currentValue;
    private Long step;
}
