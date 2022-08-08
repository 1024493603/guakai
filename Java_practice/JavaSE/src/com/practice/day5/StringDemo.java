package com.practice.day5;

import org.junit.Test;

public class StringDemo {
    @Test
    public void test1() {
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = "abc";

        //比较地址
        System.out.println(str1 == str2);// false
        System.out.println(str1 == str3);// true
        System.out.println(str2 == str3);// false

        //比较字符串是否相等
        //equals区分大小写 equalsIgnoreCase忽视大小写
        System.out.println(str1.equals(str2));// true
        System.out.println(str1.equals(str3));// true
        System.out.println(str2.equals(str3));// true
    }

    @Test
    public void test2() {
        String str = "Java AndroidA";
        char[] chars = str.toCharArray(); //String转成char数组
        char c = str.charAt(5);           //取得String的第几位
        System.out.println(c);
        System.out.println(chars[5]);
        int index = str.indexOf('A');     //找String第一次出现A的索引
        System.out.println(index);
        index = str.indexOf('A', 6);//从索引6开始
        System.out.println(index);
        index = str.indexOf("And");      //找字符串中的字符串
        System.out.println(index);

        StringBuilder builder = new StringBuilder("java");//无锁，高效率
        StringBuffer buffer = new StringBuffer("java");//有锁，低效率
        builder.append("php").append("C++").append("python");
        System.out.println(builder);
    }

    @Test
    public void test3() {
        int num = 3;
        Integer integer1 = 3; //装箱
        int n = integer1.intValue();//拆箱
        Integer integer2 = integer1 + 2;//拆箱  装箱
    }

    @Test
    public void test4(){
        Person person1 = new Person();
        Person person2 = new Person();
//        Person.static
//        Person.Person
//        Person.Person
    }
}
