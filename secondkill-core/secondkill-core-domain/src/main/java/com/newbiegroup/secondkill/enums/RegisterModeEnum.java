package com.newbiegroup.secondkill.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/7/5 16:38
 */
@AllArgsConstructor
public enum RegisterModeEnum {
    BY_PHONE(1,"通过手机号注册")
    ;

    @Getter
    int code;
    @Getter
    String value;

    public static RegisterModeEnum getEnumByCode(int code){
        for (RegisterModeEnum _enum : RegisterModeEnum.values()) {
            if (Objects.equals(code, _enum.getCode())) {
                return _enum;
            }
        }
        return null;
    }
}
