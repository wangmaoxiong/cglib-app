package com.wmx.cglibapp.beans;

import com.wmx.cglibapp.pojo.Person;
import net.sf.cglib.beans.ImmutableBean;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/17 14:14
 */
public class ImmutableBeanTest {
    public static void main(String[] args) {
        Person person = new Person();
        person.setId(9527);
        person.setName("华安");

        //创建不可变包装 bean
        Person immutablePerson = (Person) ImmutableBean.create(person);

        //输出：com.wmx.cglibapp.pojo.Person@277050dc
        System.out.println(person);
        //输出：com.wmx.cglibapp.pojo.Person$$ImmutableBeanByCGLIB$$dee8248a@5c29bfd
        System.out.println(immutablePerson);

        //输出：9527,华安
        System.out.println(immutablePerson.getId() + "," + immutablePerson.getName());
        person.setName("虚竹");
        //输出：9527,虚竹
        System.out.println(immutablePerson.getId() + "," + immutablePerson.getName());
        //如果操作 immutablePerson.setId(110);，则非法状态异常
    }
}
