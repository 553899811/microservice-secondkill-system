package com.newbiegroup.secondkill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newbiegroup.secondkill.entity.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * UserInfoMapper继承基类
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}