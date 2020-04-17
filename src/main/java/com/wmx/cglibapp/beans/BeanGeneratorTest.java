package com.wmx.cglibapp.beans;

import net.sf.cglib.beans.BeanGenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/17 14:45
 */
public class BeanGeneratorTest {
    public static void main(String[] args) {
        try {
            //1、创建 BeanGenerator 对象，同时设置 bean 的属性
            //可以使用 addProperties(BeanGenerator gen, Map props) 一次设置多个属性.
            BeanGenerator beanGenerator = new BeanGenerator();
            beanGenerator.addProperty("id", Integer.class);
            beanGenerator.addProperty("name", String.class);
            beanGenerator.addProperty("birthday", Date.class);
            //创建动态对象。后面都是 Java 原生的 API 操作.
            Object dynamicPerson = beanGenerator.create();

            //2、为动态对象属性添加 set 方法.
            Method setId = dynamicPerson.getClass().getMethod("setId", Integer.class);
            Method setName = dynamicPerson.getClass().getMethod("setName", String.class);
            Method setBirthday = dynamicPerson.getClass().getMethod("setBirthday", Date.class);

            //3、调用动态对象属性的 setter 进行赋值.
            setId.invoke(dynamicPerson, 1000);
            setName.invoke(dynamicPerson, "段誉");
            setBirthday.invoke(dynamicPerson, new Date());

            //4、为动态对象添加 getter 方法.
            Method getId = dynamicPerson.getClass().getMethod("getId");
            Method getName = dynamicPerson.getClass().getMethod("getName");
            Method getBirthday = dynamicPerson.getClass().getMethod("getBirthday");

            //5、调用 getter 方法取值.
            //输出：1000
            System.out.println(getId.invoke(dynamicPerson));
            //输出：段誉
            System.out.println(getName.invoke(dynamicPerson));
            //输出：Fri Apr 17 15:37:24 CST 2020
            System.out.println(getBirthday.invoke(dynamicPerson));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
