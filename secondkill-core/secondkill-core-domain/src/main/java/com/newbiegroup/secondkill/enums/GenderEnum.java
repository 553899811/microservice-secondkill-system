package com.newbiegroup.secondkill.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/7/29 10:53
 */
@Getter
public enum GenderEnum {
    MALE(1, "男生"),
    FEMALE(2, "女生");
    int key;
    String value;

    GenderEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
