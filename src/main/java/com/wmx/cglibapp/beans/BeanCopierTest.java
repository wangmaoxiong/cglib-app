package com.wmx.cglibapp.beans;

import com.wmx.cglibapp.pojo.Person;
import com.wmx.cglibapp.pojo.PersonDto;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/17 15:48
 */
public class BeanCopierTest {

    /**
     * {@link BeanCopier} 对象属性复制.
     * 1、BeanCopier 默认只拷贝名称和类型都相同的属性，如 int 与 Integer 也会认为类型不同，不会复制
     * 2、属性名称不匹配的，则一定不会复制；属性名称一致时，如果类型不一致，也不会复制.
     */
    public void beanCopierCopy1() {
        Person person = new Person();
        person.setId(20000);
        person.setName("齐天大圣");
        person.setMarry(false);
        person.setSalary(12050.87F);
        person.setBirthday(LocalDateTime.now());

        PersonDto personDto = new PersonDto();

        /**BeanCopier create(Class source, Class target, boolean useConverter)
         * source：源对象，其属性需要提供 getter 方法,否则不会复制
         * target：目标对象，其属性需要提供 setter 方法,否则不会复制
         * useConverter：是否使用 {@link Converter} 转换器.
         */
        BeanCopier beanCopier = BeanCopier.create(Person.class, PersonDto.class, false);
        //copy(Object from, Object to, Converter converter)：属性赋值.
        beanCopier.copy(person, personDto, null);
        //输出：Person{id=20000, name='齐天大圣', marry=false, salary=12050.87, birthday=2020-04-17T16:53:41.111}
        System.out.println(person);
        //输出：Person{id=20000, name='齐天大圣', marry=false, salary=null, birthday=null}
        System.out.println(personDto);
    }

    /**
     * 1、属性类型不同时，可以通过实现 {@link Converter} 接口来自定义转换器
     * 2、从目标对象的属性出发，如果源对象有相同名称的属性，则调一次 {@link Converter#convert} 方法.
     * 3、一旦使用 Converter 转换器，BeanCopier 就只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性。
     */
    public void beanCopierCopy2() {
        Person person = new Person();
        person.setId(25000);
        person.setName("天蓬元帅");
        person.setMarry(true);
        person.setSalary(12550.87F);
        person.setBirthday(LocalDateTime.of(2020, 02, 22, 10, 54, 10));

        PersonDto personDto = new PersonDto();

        /**BeanCopier create(Class source, Class target, boolean useConverter)
         * source：待赋值属性的 bean.
         * target：需要赋值的 bean.
         * useConverter：是否使用 {@link Converter} 转换器.
         */
        BeanCopier beanCopier = BeanCopier.create(Person.class, PersonDto.class, true);
        //copy(Object from, Object to, Converter converter)：属性赋值.
        beanCopier.copy(person, personDto, new Converter() {
            /**会遍历目标对象的属性，如果源对象有相同名称的属性，则调一次 {@link Converter#convert} 方法.
             * @param value ：源对象属性的值，如 25000.
             * @param target ：目标对象，如 class java.lang.Integer.
             * @param context ：源对象属性的 setter 方法名称.
             * @return
             */
            @Override
            public Object convert(Object value, Class target, Object context) {
                System.out.println("value=" + value.getClass() + ",target=" + target + ",context=" + context);
                if (value instanceof LocalDateTime) {
                    LocalDateTime birthday = (LocalDateTime) value;
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    return birthday.format(timeFormatter);
                } else if (value instanceof Float) {
                    return value.toString();
                } else {
                    return value;
                }
            }
        });
        //输出：Person{id=25000, name='天蓬元帅', marry=true, salary=12550.87, birthday=2020-02-22T10:54:10}
        System.out.println(person);
        //输出：Person{id=25000, name='天蓬元帅', marry=true, salary=12550.87, birthday=2020-02-22 10:54:10}
        System.out.println(personDto);
    }

    public static void main(String[] args) {
        new BeanCopierTest().beanCopierCopy2();

    }
}
