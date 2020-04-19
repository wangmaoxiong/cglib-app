package com.wmx.cglibapp.core;

import net.sf.cglib.core.KeyFactory;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/19 8:53
 */
public interface IntStringKey {
    /**
     * 使用 {@link KeyFactory} 构建接口的实例时
     * 1、接口中有且只能有 newInstance 方法，且是返回值必须是 Object，实质就是接口的实例.
     * 2、参数自定义，只要传入的参数相同，则返回的就是同样的实例.
     *
     * @param i
     * @param s
     * @return
     */
    Object newInstance(int i, String s);

}
