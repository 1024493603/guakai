package com.practice.day20.pattern.singleton;

//饿汉式
public class Singleton1 {
    private static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getINSTANCE() {
        return INSTANCE;
    }
}
