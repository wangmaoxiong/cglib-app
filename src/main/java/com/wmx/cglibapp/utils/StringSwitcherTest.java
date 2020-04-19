package com.wmx.cglibapp.utils;

import net.sf.cglib.util.StringSwitcher;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/19 11:33
 */
public class StringSwitcherTest {
    public static void main(String[] args) {
        String[] strings = new String[]{"one", "two"};
        int[] values = new int[]{10, 20};
        /**
         *StringSwitcher create(String[] strings, int[] ints, boolean fixedInput)
         * strings：字符串键的数组；必须与值数组（ints）的长度相同
         * ints：整型结果数组；必须与键数组（strings）的长度相同
         * int intValue(String s)：返回与给定键关联的整数，key 不存在时，返回 -1
         */
        StringSwitcher stringSwitcher = StringSwitcher.create(strings, values, true);
        System.out.println(stringSwitcher.intValue("one"));
        System.out.println(stringSwitcher.intValue("two"));
        System.out.println(stringSwitcher.intValue("three"));
    }
}
