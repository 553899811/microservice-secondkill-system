package com.newbiegroup.secondkill.manager;

import com.newbiegroup.secondkill.entity.UserInfo;
import com.newbiegroup.secondkill.entity.UserPassword;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/5/5 21:22
 */
public interface UserPasswordManager {

    UserPassword selectByUserId(Long userId);

    boolean create(UserPassword userPasswordDO);
}
