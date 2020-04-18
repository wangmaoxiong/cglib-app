package com.wmx.cglibapp.beans;

import com.wmx.cglibapp.pojo.User;
import net.sf.cglib.beans.BeanMap;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/18 21:29
 */
public class BeanMapTest {
    public static void main(String[] args) {
        User person = new User();
        person.setId(2002);
        person.setName("刘邦");

        //BeanMap create(Object bean)：bean 对象的属性需要提供 getter、setter 方法.
        BeanMap personBeanMap = BeanMap.create(person);
        //输出：{birthday=null, marry=null, name=刘邦, id=2002, salary=null}
        System.out.println(personBeanMap);
        personBeanMap.put("id", 3003);
        //输出：Person{id=3003, name='刘邦', marry=null, salary=null, birthday=null}
        System.out.println(person);
        person.setMarry(false);
        //输出：{birthday=null, marry=false, name=刘邦, id=3003, salary=null}
        System.out.println(personBeanMap);
    }

}
