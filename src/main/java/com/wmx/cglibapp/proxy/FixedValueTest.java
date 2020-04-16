package com.wmx.cglibapp.proxy;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/16 19:17
 */
public class FixedValueTest {
    public String toGreet() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(new Date()) + "：hello cglib.");
        return dateFormat.format(new Date());
    }

    public static void main(String[] args) {
        //Enhancer 中只有一个无参构造器
        Enhancer enhancer = new Enhancer();
        //setSuperclass(Class superclass)：设置将扩展的类，superclass：要扩展的超类或要实现的接口
        enhancer.setSuperclass(FixedValueTest.class);
        //interface FixedValue extends Callback：对拦截的所有方法返回固定的值
        enhancer.setCallback(new FixedValue() {
            //返回的对象必须与的目标类拦截方法返回类型兼容，否则抛异常.
            @Override
            public Object loadObject() throws Exception {
                System.out.println("FixedValue loadObject is run.");
                ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
                objectNode.put("code", 200);
                objectNode.put("msg", "success");
                /**比如上面的 toGreet 方法返回的 String 类型，如果这里返回 {@link ObjectNode} 类型，则抛 ClassCastException 异常 */
                return objectNode.toString();
            }
        });
        //如果需要，生成一个新类并使用指定的回调（如果有）以创建新的对象实例。使用超类的无参数构造函数创建。
        FixedValueTest fixedValueTest = (FixedValueTest) enhancer.create();
        String toGreet = fixedValueTest.toGreet();
        //输出：class com.wmx.cglibapp.proxy.FixedValueTest$$EnhancerByCGLIB$$24849247
        //类名由 cglib 随机生成，以避免命名冲突
        System.out.println(fixedValueTest.getClass());
        //输出：{"code":200,"msg":"success"}
        System.out.println(toGreet);
    }
}
