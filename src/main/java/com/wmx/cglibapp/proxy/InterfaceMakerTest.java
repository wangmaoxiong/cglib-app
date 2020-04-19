package com.wmx.cglibapp.proxy;

import com.wmx.cglibapp.pojo.User;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.InterfaceMaker;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;


/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/19 12:58
 */
public class InterfaceMakerTest {
    public static void main(String[] args) {
        /**
         * Signature(String name, Type returnType, Type[] argumentTypes)：接口中的方法签名
         * name：接口中的方法名称.
         * returnType：方法返回值类型.
         * argumentTypes：方法的参数类型数组.
         */
        Signature additionSignature = new Signature("find", Type.getType(String.class), new Type[]{Type.INT_TYPE, Type.getType(String.class)});
        Signature multiplicationSignature = new Signature("delete", Type.VOID_TYPE, new Type[]{Type.getType(Map.class)});

        //add(Signature sig, Type[] exceptions)：往InterfaceMaker中添加方法签名，exceptions 是方法抛出的异常类型.
        InterfaceMaker interfaceMaker = new InterfaceMaker();
        interfaceMaker.add(additionSignature, new Type[0]);
        interfaceMaker.add(multiplicationSignature, new Type[0]);

        //创建接口
        Class mathematics = interfaceMaker.create();
        //输出：interface net.sf.cglib.empty.Object$$InterfaceMakerByCGLIB$$7ef20235
        System.out.println(mathematics);

        Method[] methods = mathematics.getMethods();
        for (Method method : methods) {
            //输出：find,class java.lang.String,[int, class java.lang.String]
            //输出：delete,void,[interface java.util.Map]
            System.out.println(method.getName() + "," + method.getReturnType() + "," + Arrays.asList(method.getParameterTypes()));
        }
    }
}
