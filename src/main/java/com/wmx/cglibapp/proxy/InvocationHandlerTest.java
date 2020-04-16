package com.wmx.cglibapp.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能更强大的回调类InvocationHandler
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/16 19:47
 */
public class InvocationHandlerTest {
    public String toGreet() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(new Date()) + "：hello InvocationHandler.");
        return dateFormat.format(new Date());
    }

    public static void main(String[] args) {
        //Enhancer 中只有一个无参构造器
        Enhancer enhancer = new Enhancer();
        //setSuperclass(Class superclass)：设置将扩展的类，superclass：要扩展的超类或要实现的接口
        enhancer.setSuperclass(InvocationHandlerTest.class);
        /**
         * interface InvocationHandler extends Callback：调用处理程序.
         */
        enhancer.setCallback(new InvocationHandler() {
            //返回返回值类型必须于拦截到的方法的返回值类型一致，返回 ClassCastException
            //如果方法内部做类似 method.invoke(proxy,args) 操作，则会进入死循环.
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /**Class<?> getDeclaringClass() ：获取声明类，{@link Method#getDeclaringClass()}
                 * Class<?> getReturnType()：方法返回值类型，{@link Method#getReturnType()}
                 */
                System.out.println(method.getDeclaringClass());
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "Invocation Success !";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });
        //创建增强类
        InvocationHandlerTest invocationHandlerTest = (InvocationHandlerTest) enhancer.create();
        //假如这里调用 invocationHandlerTest.toString()，则上面抛运行时异常,因为方法的声明类为 Object
        String toGreet = invocationHandlerTest.toGreet();
        System.out.println(toGreet);
    }
}
