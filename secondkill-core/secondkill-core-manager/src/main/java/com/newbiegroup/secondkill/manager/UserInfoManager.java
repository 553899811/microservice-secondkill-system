package com.newbiegroup.secondkill.manager;

import com.newbiegroup.secondkill.entity.UserInfo;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/5/5 21:23
 */
public interface UserInfoManager {
    /**
     * id 数据主键;
     * @param id
     * @return
     */
    UserInfo selectById(Long id);

    UserInfo getUserByUserId(Long userId);

    UserInfo getUserByTelPhone(String telPhone);

    void create(UserInfo userDO);
}
