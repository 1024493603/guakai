package com.practice.day5;

public class Singleton {
    //在类内维护唯一的实例
    private static final Singleton INSTANCE = new Singleton();
    //构造方法私有化
    //不在类内实例化则可以阻止用户实例化这个类
    private Singleton() {

    }
}
