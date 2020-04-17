package com.wmx.cglibapp.proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/15 16:53
 */
public class HelloWorld {
    /**
     * 目标对象需要提供一个无参过构造器
     */
    public HelloWorld() {
        System.out.println("HelloWorld constructors is run.");
    }

    public void toGreet() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(new Date()) + "：hello cglib.");
    }

    public static void main(String[] args) {
        //Enhancer 中只有一个无参构造器
        Enhancer enhancer = new Enhancer();
        //setSuperclass(Class superclass)：设置将扩展的类，superclass：要扩展的超类或要实现的接口
        enhancer.setSuperclass(HelloWorld.class);
        /**setCallback(final Callback callback)
         * 设置要使用的单个 Callback，如果使用 {@link Enhancer#createClass()}，则会忽略，
         * callback 用于所有方法的回调
         * {@link MethodInterceptor} 方法拦截接口，继承了 {@link Callback}，目标对象的所有方法调用时都会被 intercept 方法拦截.
         */
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        //如果需要，生成一个新类并使用指定的回调（如果有）以创建新的对象实例。使用超类的无参数构造函数创建。
        HelloWorld helloWorld = (HelloWorld) enhancer.create();
        System.out.println("-------1----------");
        //这里调用方法时，上面的 MethodInterceptor 就会拦截.
        helloWorld.toGreet();
        System.out.println("-------2---从 Object 继承的 toString 方法，可以被拦截-------");
        helloWorld.toString();
        System.out.println("-------3---从 Object 继承的 getClass 方法，final 修饰的方法无法被拦截-------");
        helloWorld.getClass();
    }
}
