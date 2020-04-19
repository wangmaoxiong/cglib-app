package com.wmx.cglibapp.mixins;

import com.wmx.cglibapp.pojo.User;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/19 10:49
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("华安");
        user.setMarry(false);
        user.setSalary(9999.99F);
        return user;
    }
}
