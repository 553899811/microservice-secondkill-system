package com.newbiegroup.secondkill.config;

import com.newbiegroup.secondkill.common.exception.BizBusinessError;
import com.newbiegroup.secondkill.common.exception.BusinessException;
import com.newbiegroup.secondkill.common.response.WebResponse;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/6/19 15:51
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebResponse doError(HttpServletRequest httpServletRequest,
                                                    HttpServletResponse httpServletResponse, Exception ex) {
        ex.printStackTrace();
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            responseData.put("errCode", businessException.getCode());
            responseData.put("errMsg", businessException.getMessage());
        } else if (ex instanceof ServletRequestBindingException) {
            responseData.put("errCode", BizBusinessError.UNKNOWN_COMMON_ERROR.getCode());
            responseData.put("errMsg", "url绑定路由问题");
        } else if (ex instanceof NoHandlerFoundException) {
            responseData.put("errCode", BizBusinessError.UNKNOWN_COMMON_ERROR.getCode());
            responseData.put("errMsg", "没有找到对应的访问路径");
        } else {
            responseData.put("errCode", BizBusinessError.UNKNOWN_COMMON_ERROR.getCode());
            responseData.put("errMsg", BizBusinessError.UNKNOWN_COMMON_ERROR.getMessage());
        }
        return WebResponse.create(String.valueOf(responseData.get("errCode")), ex.getMessage(), responseData);
    }
}
