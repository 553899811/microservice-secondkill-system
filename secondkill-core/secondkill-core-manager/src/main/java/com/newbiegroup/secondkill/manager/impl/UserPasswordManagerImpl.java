package com.newbiegroup.secondkill.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbiegroup.secondkill.common.exception.BusinessException;
import com.newbiegroup.secondkill.dao.UserPasswordMapper;
import com.newbiegroup.secondkill.entity.UserPassword;
import com.newbiegroup.secondkill.manager.UserPasswordManager;
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
 * @date 2021/5/5 21:23
 */
@Slf4j
@Service
public class UserPasswordManagerImpl extends ServiceImpl<UserPasswordMapper, UserPassword> implements UserPasswordManager {

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    @Override
    public UserPassword selectByUserId(Long userId) {
        log.info("userPasswordManager selectByUserId,userId:{}", userId);
        QueryWrapper query = new QueryWrapper();
        query.eq("user_id", userId);
        UserPassword res = userPasswordMapper.selectOne(query);
        return Optional.ofNullable(userPasswordMapper.selectOne(query))
                .<BusinessException>orElseThrow(() -> new BusinessException("根据用户ID 查询手机号不存在"));
    }

    @Override
    public boolean create(UserPassword entity) {
        return super.save(entity);
    }
}
