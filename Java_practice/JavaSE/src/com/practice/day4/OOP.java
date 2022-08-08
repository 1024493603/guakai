package com.practice.day4;

import org.junit.Test;

public class OOP {
    public static void main(String[] args) {
        Student kk = new Student();
        Student mm = new Student(2, "m", 20);
        Student ll = new Student(3, "l");
        kk.setId(1);
        kk.setName("k");
        kk.setAge(10);
        System.out.println(kk);
        System.out.println(mm);
        System.out.println(ll);
    }

    @Test
    public void test1() {
        /*
        空指针报错
        java.lang.NullPointerException
        Student stu = null;
        System.out.println(stu.getAge());
        */
        Teacher teach = new Teacher();
        //com.practice.day4.Teacher@22927a81
        //调用了Object里面的toString()
        System.out.println(teach.toString());

        Student stu = new Student();
        //定义后则调用自己定的
        System.out.println(stu.toString());
    }

    @Test
    public void test2() {
        Person person = new Student();
        //person无法使用Student类里的Study接口
        //因为person是以Person为角度看，所以无法看到Student的Study
    }
}