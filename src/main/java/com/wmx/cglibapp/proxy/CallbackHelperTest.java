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


    public static void main(String[] args) {

        CallbackHelper callbackHelper = new CallbackHelper(CallbackHelperTest.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                System.out.println("methodName="+method.getName());
                System.out.println("declaringClass="+method.getDeclaringClass());
                System.out.println("returnType="+method.getReturnType());
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return new MethodInterceptor() {
                        @Override
                        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                            System.out.println("--------"+method.getName());
                            return methodProxy.invokeSuper(o, objects);
                        }
                    };
                } else {
                    System.out.println("00000000000000000");
                    return NoOp.INSTANCE;
                }
            }
        };

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CallbackHelperTest.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());

        CallbackHelperTest callbackHelperTest = (CallbackHelperTest) enhancer.create();
//        String greet = callbackHelperTest.toGreet("wangMX");
//        System.out.println(greet);
callbackHelperTest.toString();
    }

}
