package com.newbiegroup.secondkill.vo;

import com.newbiegroup.secondkill.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/6/19 23:00
 */
@Setter
@Getter
public class UserVO {
    /**
     * 用户ID;
     */
    private Long userId;
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotNull(message = "性别不能不填写")
    private Byte gender;

    @NotNull(message = "年龄不能不填写")
    @Min(value = 0, message = "年龄必须大于0岁")
    @Max(value = 150, message = "年龄必须小于150岁")
    private Integer age;

    @NotBlank(message = "手机号不能为空")
    private String telPhone;

    /**
     * 用户注册邮箱;
     */
    @NotBlank(message = "注册邮箱不能为空")
    private String email;
    private Integer registerMode;
    private String thirdPartyId;
    private String encrptPassword;

    public static UserVO getInstance(UserInfo entity) {
        if (entity == null) {
            return null;
        }
        UserVO target = new UserVO();
        BeanUtils.copyProperties(entity, target, new String[]{});
        return target;
    }
}
