package com.practice.day20.pattern.singleton;

//懒汉式
public class Singleton2 {
    private static Singleton2 INSTANCE;

    private Singleton2() {}

    public synchronized static Singleton2 getINSTANCE() {
        //用if有线程安全问题
        //加锁可以解决
        if (INSTANCE == null) {
            INSTANCE = new Singleton2();
        }

        return INSTANCE;
    }
}
