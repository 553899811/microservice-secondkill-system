package com.newbiegroup.secondkill.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 
 * 
 */
@Setter
@Getter
public class UserPassword implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 密文密码
     */
    private String encryptPassword;
}