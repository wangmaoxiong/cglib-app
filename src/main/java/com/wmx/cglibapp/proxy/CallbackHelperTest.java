package com.wmx.cglibapp.proxy;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/17 6:49
 */
public class CallbackHelperTest {
    public String toGreet(String msg) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(new Date()) + "：hello CallbackHelper.");
        return dateFormat.format(new Date()) + "\t" + msg;
    }

    public void showInfo() {
        System.out.println("I am ok!");
    }

    public static void main(String[] args) {
        /**
         * {@link CallbackHelper(Class superclass, Class[] interfaces)} 回调辅助类.
         * 会对目标对象 superclass 中的所有方法先过滤一遍，包括继承的方法，都会进入 getCallback 方法.
         * getCallback 方法中使用条件判断，对于满足的条件的方法，返回 {@link Callback} 回调实现，将来这个方法被调用的时候就会 intercept 拦截
         * interface NoOp extends Callback：使用此回调的实现，将直接委托给基类中的默认（超级）实现，即直接调用目标方法，不再进行拦截.
         */
        CallbackHelper callbackHelper = new CallbackHelper(CallbackHelperTest.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                System.out.println("methodName=" + method.getName() + ", returnType=" + method.getReturnType() + ", declaringClass=" + method.getDeclaringClass());
                if (method.getName().equals("toGreet")) {
                    System.out.println("√ 方法将来会被拦截.\n");
                    return new MethodInterceptor() {
                        @Override
                        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                            System.out.println("★ 拦截到方法：" + method.getName());
                            return methodProxy.invokeSuper(o, objects);
                        }
                    };
                } else {
                    System.out.println("✘ 方法将来不会被拦截.\n");
                    return NoOp.INSTANCE;
                }
            }
        };
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CallbackHelperTest.class);
        //设置生成代理类时要使用的回调过滤器
        enhancer.setCallbackFilter(callbackHelper);
        //setCallbacks(Callback[] callbacks)：因为 CallbackHelper 的 getCallback 返回的是各个方法的回调实现，所以是一个数组
        enhancer.setCallbacks(callbackHelper.getCallbacks());

        CallbackHelperTest callbackHelperTest = (CallbackHelperTest) enhancer.create();
        //根据规则 toGreet 方法会被上面 MethodInterceptor 的 intercept 方法拦截到
        String greet = callbackHelperTest.toGreet("wangMX");
        System.out.println(greet + "\n");
        //根据规则 showInfo 方法不会被拦截，而会直接调用目标方法.
        callbackHelperTest.showInfo();
    }
}
