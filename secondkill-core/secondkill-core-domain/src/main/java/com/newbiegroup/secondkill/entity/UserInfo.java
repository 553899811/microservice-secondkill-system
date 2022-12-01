package com.newbiegroup.secondkill.entity;

import com.newbiegroup.secondkill.vo.UserVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 下单用户信息
 * @author
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -266053277829135134L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * // 1 代表男性 ;2 代表女性
     */
    private Byte gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 用户手机号码
     */
    private String telPhone;

    /**
     * 用户注册邮箱;
     */
    private String email;

    /**
     * 注册方式
     */
    private Integer registerMode;

    /**
     * 第三方ID
     */
    private String thirdPartyId;

    public static UserInfo getInstance(UserVO entity) {
        if (entity == null) {
            return null;
        }
        UserInfo target = new UserInfo();
        BeanUtils.copyProperties(entity, target, new String[]{});
        return target;
    }
}