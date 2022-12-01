package com.newbiegroup.secondkill.service.impl;

import com.newbiegroup.secondkill.common.exception.BizBusinessError;
import com.newbiegroup.secondkill.common.exception.BusinessException;
import com.newbiegroup.secondkill.common.utils.EncodeUtil;
import com.newbiegroup.secondkill.common.utils.GenerateNoUtil;
import com.newbiegroup.secondkill.entity.UserInfo;
import com.newbiegroup.secondkill.entity.UserPassword;
import com.newbiegroup.secondkill.manager.impl.UserInfoManagerImpl;
import com.newbiegroup.secondkill.manager.impl.UserPasswordManagerImpl;
import com.newbiegroup.secondkill.service.UserInfoService;
import com.newbiegroup.secondkill.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/6/19 23:04
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoManagerImpl userInfoManager;
    @Autowired
    private UserPasswordManagerImpl userPasswordManager;

    @Override
    public UserVO getUserById(Long id) {
        log.info("userService getUserById,id:{}", id);
        UserInfo userDO = userInfoManager.selectById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);
        return userVO;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void register(UserVO userVO) {
        Long userId = GenerateNoUtil.generateUserId();
        userVO.setUserId(userId);
        UserInfo userDO = UserInfo.getInstance(userVO);
        try {
            userInfoManager.create(userDO);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BizBusinessError.PARAMETER_VALIDATION_ERROR, "手机号已注册，请更换");
        }
        UserPassword userPasswordDO = convertToPasswordFromUserInfo(userVO);
        userPasswordManager.create(userPasswordDO);
        return;
    }

    @Override
    public UserVO validateLogin(String telPhone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserInfo userInfo = userInfoManager.getUserByTelPhone(telPhone);
        UserPassword userPassword = userPasswordManager.selectByUserId(userInfo.getUserId());
        UserVO userVO = convertToUserFromPassword(userInfo, userPassword);
        String encrptPassword = EncodeUtil.encodeByMd5(password);
        if (!StringUtils.equals(encrptPassword, userVO.getEncrptPassword())) {
            throw new BusinessException("输入密码不正确");
        }
        return userVO;
    }

    private UserPassword convertToPasswordFromUserInfo(UserVO userVO) {
        if (userVO == null) {
            return null;
        }
        UserPassword userPassword = new UserPassword();
        userPassword.setUserId(userVO.getUserId());
        userPassword.setEncryptPassword(userVO.getEncrptPassword());
        return userPassword;
    }

    private UserVO convertToUserFromPassword(UserInfo userInfo, UserPassword userPassword) {
        if (userInfo == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userInfo, userVO);
        if (userPassword != null) {
            userVO.setEncrptPassword(userPassword.getEncryptPassword());
        }
        return userVO;
    }
}
