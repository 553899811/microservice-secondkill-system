package com.newbiegroup.secondkill.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/10/11 18:21
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -755380268887733350L;

    private Long id;

    private int yn;

    private Date created;

    private Date modified;
}
