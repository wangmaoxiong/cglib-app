package com.wmx.cglibapp.beans;

import com.wmx.cglibapp.pojo.User;
import net.sf.cglib.beans.BulkBean;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/18 21:56
 */
public class BulkBeanTest {
    private static String[] getters = new String[]{"getId", "getName", "getMarry", "getSalary", "getBirthday"};
    private static String[] setters = new String[]{"setId", "setName", "setMarry", "setSalary", "setBirthday"};
    private static Class[] types = new Class[]{Integer.class, String.class, Boolean.class, Float.class, LocalDateTime.class};
    /**
     * BulkBean create(Class target, String[] getters, String[] setters, Class[] types)
     * target：关联的目标 java bean，需要提供 getter、setter 方法.
     * getters：target 中的 getter 方法名称集合，目标类中必须存在.
     * setters：target 中的 setter 方法名称集合，目标类中必须存在.
     * types：target 中属性的数据类型.
     * 关联后 target 与  bulkBean 的修改都会影响对方.
     */
    private static BulkBean bulkBean = BulkBean.create(User.class, getters, setters, types);

    /**
     * getPropertyValues(Object bean)：获取 bean 的全部属性值.
     * setPropertyValues(Object bean, Object[] values)：为 bean 的所有属性设值
     */
    public void test1() {
        User user = new User();
        //必须按着 setter 方法的顺序赋值
        bulkBean.setPropertyValues(user, new Object[]{1009, "刘秀", false, 10009.88F, LocalDateTime.now()});
        //输出：User{id=1009, name='刘秀', marry=false, salary=10009.88, birthday=2020-04-18T22:05:19.513}
        System.out.println(user);
        //按着 getter 方法的顺序返回.
        Object[] values = bulkBean.getPropertyValues(user);
        //输出：User{id=1009, name='刘秀', marry=false, salary=10009.88, birthday=2020-04-18T22:06:12.540}
        System.out.println(Arrays.asList(values));
        //输出：User{id=1009, name='刘秀', marry=false, salary=10009.88, birthday=2020-04-18T22:09:23.362}
        System.out.println(user);
        user.setName("刘启");
        Object value = bulkBean.getPropertyValues(user)[1];
        //输出：刘启
        System.out.println(value);
    }

    /**
     * 实现 BeanCopier#copy(java.lang.Object, java.lang.Object, net.sf.cglib.core.Converter) 的属性赋值功能.
     */
    public void test2() {
        User user = new User();
        user.setId(4000);
        user.setName("刘景");
        user.setMarry(true);
        user.setSalary(8888998.9F);

        User userCone = new User();
        bulkBean.setPropertyValues(userCone, bulkBean.getPropertyValues(user));
        userCone.setBirthday(LocalDateTime.now());
        System.out.println(user);
        System.out.println(userCone);

    }

    public static void main(String[] args) {

        new BulkBeanTest().test2();
    }
}
