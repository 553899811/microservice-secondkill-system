package com.newbiegroup.secondkill.common.exception;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/5/19 13:37
 */
public interface CommonError {
    public int getCode();
    public String getMessage();
    public CommonError setMessage(String errMsg);
}
