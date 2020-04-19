package com.wmx.cglibapp.mixins;

/**
 * 混合接口继承需要进行合并的接口，注意父接口中的方法继承到本接口中不能冲突，如方法名称和参数完全一致.
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/19 10:52
 */
public interface MixinUserPerson extends PersonService, UserService {
}
