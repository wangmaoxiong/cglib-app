package com.wmx.cglibapp.mixins;

import net.sf.cglib.proxy.Mixin;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/19 10:53
 */
public class MixinTest {
    public static void main(String[] args) {
        /**
         * Mixin create(Class[] interfaces, Object[] delegates)
         * interfaces：待混合的接口数组，将混合接口放在最后，因为需要混合接口需要继承各个接口，所以方法名称和参数不能冲突.
         * delegates：待混合接口的实现类，按顺序对应.
         * Mixin newInstance(Object[] delegates)
         */
        Class[] interfaces = new Class[]{UserService.class, PersonService.class, MixinUserPerson.class};
        Object[] delegates = new Object[]{new UserServiceImpl(), new PersonServiceImpl()};
        Mixin mixin = Mixin.create(interfaces, delegates);
        MixinUserPerson mixinUserPerson = (MixinUserPerson) mixin;
        //输出：Person{id=1000, name='刘秀', marry=null, salary=15000.88, birthday=1993-08-25T05:02}
        System.out.println(mixinUserPerson.getPerson(1000));
        //输出：User{id=2500, name='华安', marry=false, salary=9999.99, birthday=null}
        System.out.println(mixinUserPerson.getUser(2500));
    }
}
