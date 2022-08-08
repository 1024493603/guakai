package com.practice.day20.pattern.singleton;

import org.junit.Test;

public class PatternDemo {

    @Test
    public void test1(){
        Singleton1 instance1 = Singleton1.getINSTANCE();
        Singleton1 instance2 = Singleton1.getINSTANCE();

        //调用几次都是一个
        System.out.println(instance1 == instance2);//true


        Singleton2 instance3 = Singleton2.getINSTANCE();
        Singleton2 instance4 = Singleton2.getINSTANCE();

        //调用几次都是一个
        System.out.println(instance3 == instance4);//true
    }

}
