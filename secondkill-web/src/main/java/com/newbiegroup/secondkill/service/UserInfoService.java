package com.newbiegroup.secondkill.service;

import com.newbiegroup.secondkill.vo.UserVO;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/6/19 23:03
 */
public interface UserInfoService {

    /**
     * 通过用户ID获取用户对象的方法
     * @param userId
     * @return
     */
    UserVO getUserById(Long userId);

    void register(UserVO userVO);

    UserVO validateLogin(String telPhone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
