package com.wmx.cglibapp.mixins;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/19 10:52
 */
public interface MixinUserPerson extends PersonService, UserService {
    void show();
}
