package com.newbiegroup.secondkill.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/5/19 13:39
 */
@AllArgsConstructor
@Getter
public enum BizBusinessError implements CommonError {
    //通用错误类型9999
    UNKNOWN_COMMON_ERROR(9999, "通用未知错误"),
    PARAMETER_VALIDATION_ERROR(10000, "参数不合法"),
    // 登录校验
    USER_NON_LOGIN(1000,"当前用户未登录")
    ;

    private int code;
    private String message;


    @Override
    public CommonError setMessage(String message) {
        this.message = message;
        return this;
    }
}
