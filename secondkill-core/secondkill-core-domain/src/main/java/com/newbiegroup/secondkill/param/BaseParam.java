package com.newbiegroup.secondkill.param;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/10/10 21:43
 */
@Data
public class BaseParam {
    private Long userId;
    private Date created;
    private Date modified;
}
