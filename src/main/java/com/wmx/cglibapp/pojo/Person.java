package com.wmx.cglibapp.pojo;

import java.time.LocalDateTime;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/17 14:15
 */
public class Person {
    private Integer id;
    private String name;
    private Boolean marry;
    private Float salary;
    private LocalDateTime birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMarry() {
        return marry;
    }

    public void setMarry(Boolean marry) {
        this.marry = marry;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marry=" + marry +
                ", salary=" + salary +
                ", birthday=" + birthday +
                '}';
    }
}
