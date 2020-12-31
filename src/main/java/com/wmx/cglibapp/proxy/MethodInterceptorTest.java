package com.wmx.cglibapp.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/16 20:17
 */
public class MethodInterceptorTest {

    public String toGreet(String msg) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(new Date()) + "：hello MethodInterceptor.");
        return dateFormat.format(new Date()) + "\t" + msg;
    }

    public static void main(String[] args) {
        //在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\wmx\\temp\\redisClear");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MethodInterceptorTest.class);

        // interface MethodInterceptor extends Callback：方法拦截器，目标方法执行前会被拦截.
        enhancer.setCallback(new MethodInterceptor() {
            //intercept 方法返回的数据类型，必须与拦截方法的实际返回类型兼容，否则类型转换异常.
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                //第一次输出：[wmx]、第二次输出：[]
                System.out.println(Arrays.asList(args));
                // 如果调用的方法是继承的 Object 类的，则返回固定字符串，反则调用代理类.
                if (method.getDeclaringClass() == Object.class) {
                    return "Hello Object !";
                } else {
                    /**Object invokeSuper(Object obj, Object[] args)：对指定的对象调用原始（超级）方法.
                     *  obj：增强对象，必须是作为 MethodInterceptor 的第一个参数传递的对象
                     *  args：传递给被拦截方法的参数；这里可以替换为不同的参数数组，只要类型兼容
                     */
                    //替换传入的参数
                    args = new Object[]{"新化大熊山"};
                    return proxy.invokeSuper(obj, args);
                }
            }
        });
        MethodInterceptorTest methodInterceptorTest = (MethodInterceptorTest) enhancer.create();
        //
        String toGreet = methodInterceptorTest.toGreet("wmx");
        //输出：2020-04-16 20:35:20	新化大熊山
        System.out.println(toGreet);
        //输出：Hello Object !
        System.out.println(methodInterceptorTest.toString());
    }
}
