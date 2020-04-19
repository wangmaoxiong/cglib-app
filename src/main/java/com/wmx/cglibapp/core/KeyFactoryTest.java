package com.wmx.cglibapp.core;

import net.sf.cglib.core.KeyFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/19 8:54
 */
public class KeyFactoryTest {
    /**
     * KeyFactory create(Class keyInterface)：为 keyInterface 接口创建实例.
     * keyInterface 接口中有且只能有 newInstance 方法，参数自定义，返回值必须为 Object
     */
    private static IntStringKey intStringKey = (IntStringKey) KeyFactory.create(IntStringKey.class);

    public void test1() {
        //输出：class com.wmx.cglibapp.core.IntStringKey$$KeyFactoryByCGLIB$$db45eaf9
        System.out.println(intStringKey.getClass());
        //此时未赋值，属性使用默认值，输出：0, null。
        System.out.println(intStringKey);

        Object key1 = intStringKey.newInstance(200, "success");
        Object key2 = intStringKey.newInstance(200, "success");
        Object key3 = intStringKey.newInstance(1000, "success");
        //输出：true。可见 newInstance 返回的对象就是接口实例.
        System.out.println(key1 instanceof IntStringKey);

        //输出：200, success || 200, success || 1000, success
        System.out.println(key1 + " || " + key2 + " || " + key3);

        //输出：1142983326,1142983326,1516633726。key1 与 key2 的 hashCode 值是一致的，参数相同返回的就是同样的实例.
        System.out.println(key1.hashCode() + "," + key2.hashCode() + "," + key3.hashCode());

        //输出：true,false
        System.out.println(key1.equals(key2) + "," + key1.equals(key3));
    }

    public void test2() {
        Object key1 = intStringKey.newInstance(1, "A");
        Map<Object, Object> dataMap = new HashMap<>(4);
        dataMap.put(key1, "hello KeyFactory");
        //输出：hello KeyFactory。参数相同，返回的也是同样的实例.
        System.out.println(dataMap.get(intStringKey.newInstance(1, "A")));
        //输出：null
        System.out.println(dataMap.get(intStringKey.newInstance(1, "B")));
    }

    public static void main(String[] args) {
        new KeyFactoryTest().test2();
    }
}
