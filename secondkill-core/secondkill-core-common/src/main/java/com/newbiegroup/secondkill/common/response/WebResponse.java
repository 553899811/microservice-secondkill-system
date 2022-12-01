package com.newbiegroup.secondkill.common.response;

import com.newbiegroup.secondkill.common.constants.ResultConstants;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/5/6 23:46
 */
@Setter
@Getter
public class WebResponse<T> implements Serializable {

    private static final String SUCCESS = ResultConstants.CODE_SUCC;
    private static final String FAILED = ResultConstants.CODE_FAILED;

    /**
     * 接口返回码
     */
    private String code;

    /**
     * 接口返回描述
     */
    private String message;

    /**
     * 接口返回数据
     */
    private T data;

    public WebResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> WebResponse<T> create(String code, String message, T data) {
        return new WebResponse(code, message, data);
    }

    /**
     * 构造通用成功返回对象
     *
     * @return Escmessage
     */
    @SuppressWarnings("rawtypes")
    public static <T> WebResponse<T> success(String message, T obj) {
        return create(SUCCESS, message, obj);
    }


    /**
     * 构造通用成功返回对象
     *
     * @return Escmessage
     */
    @SuppressWarnings("rawtypes")
    public static <T> WebResponse<T> success(T obj) {
        return create(SUCCESS, "SUCCESS", obj);
    }

    /**
     * 构造通用成功返回对象
     *
     * @return Escmessage
     */
    @SuppressWarnings("rawtypes")
    public static <T> WebResponse<T> success() {
        return create(SUCCESS, "SUCCESS", null);
    }

    /**
     * 构造失败返回对象，自定义code/message，
     *
     * @param code
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> WebResponse<T> failed(String code, String message) {
        WebResponse ret = create(code, message, null);
        return ret;
    }

    public static <T> WebResponse<T> failed(String message) {
        WebResponse ret = create(FAILED, message, null);
        return ret;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess(){
        return SUCCESS.equals(this.code);
    }
}
