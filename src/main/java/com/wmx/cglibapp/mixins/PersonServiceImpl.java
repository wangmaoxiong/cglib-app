package com.wmx.cglibapp.mixins;

import com.wmx.cglibapp.pojo.Person;

import java.time.LocalDateTime;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/19 10:48
 */
public class PersonServiceImpl implements PersonService {
    @Override
    public Person getPerson(Integer id) {
        Person person = new Person();
        person.setId(id);
        person.setName("刘秀");
        person.setSalary(15000.88F);
        person.setBirthday(LocalDateTime.of(1993, 8, 25, 5, 2, 0));
        return person;
    }
}
