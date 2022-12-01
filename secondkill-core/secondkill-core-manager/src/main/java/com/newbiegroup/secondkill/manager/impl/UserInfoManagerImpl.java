package com.newbiegroup.secondkill.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbiegroup.secondkill.common.exception.BusinessException;
import com.newbiegroup.secondkill.dao.UserInfoMapper;
import com.newbiegroup.secondkill.entity.UserInfo;
import com.newbiegroup.secondkill.manager.UserInfoManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/5/5 21:24
 */
@Slf4j
@Service
public class UserInfoManagerImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoManager {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectById(Long id) {
        log.info("userInfoManager getUserById,id:{}", id);
        return Optional.ofNullable(userInfoMapper.selectById(id))
                .<BusinessException>orElseThrow(() -> new BusinessException("查询用户不存在"));
    }

    @Override
    public UserInfo getUserByUserId(Long userId) {
        QueryWrapper query = new QueryWrapper();
        query.eq("user_id", userId);
        return Optional.ofNullable(userInfoMapper.selectOne(query))
                .<BusinessException>orElseThrow(() -> new BusinessException("根据用户ID查询用户不存在"));
    }

    @Override
    public UserInfo getUserByTelPhone(String telPhone) {
        QueryWrapper query = new QueryWrapper();
        query.eq("tel_phone", telPhone);
        return Optional.ofNullable(userInfoMapper.selectOne(query))
                .<BusinessException>orElseThrow(() -> new BusinessException("根据手机号 查询用户不存在"));
    }

    @Override
    public void create(UserInfo userDO) {
        int create = userInfoMapper.insert(userDO);
    }
}
