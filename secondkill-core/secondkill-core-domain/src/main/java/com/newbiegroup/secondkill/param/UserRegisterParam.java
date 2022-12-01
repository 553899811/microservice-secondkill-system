package com.newbiegroup.secondkill.param;

import lombok.Data;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/1 16:04
 */
@Data
public class UserRegisterParam extends BaseParam {
    String telPhone;
    String email;
    String otpCode;
    String userName;
    Integer gender;
    Integer age;
    String password;
}
