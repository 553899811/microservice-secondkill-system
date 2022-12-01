package com.newbiegroup.secondkill.common.exception;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/5/19 12:53
 */
public class BusinessException extends RuntimeException implements CommonError {

    private CommonError commonError;

    //直接接收 BusinessError的传参用于构造业务异常
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    public BusinessException(String message) {
        super();
        this.commonError = BizBusinessError.UNKNOWN_COMMON_ERROR;
        this.commonError.setMessage(message);
    }

    //接收自定义errMsg的方式构造业务异常
    public BusinessException(CommonError commonError, String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setMessage(errMsg);
    }


    @Override
    public int getCode() {
        return this.commonError.getCode();
    }

    @Override
    public String getMessage() {
        return this.commonError.getMessage();
    }

    @Override
    public CommonError setMessage(String errMsg) {
        this.commonError.setMessage(errMsg);
        return this;
    }

    public CommonError getCommonError() {
        return commonError;
    }
}
