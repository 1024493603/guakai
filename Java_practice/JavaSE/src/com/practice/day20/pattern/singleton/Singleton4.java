package com.practice.day20.pattern.singleton;

//枚举饿汉式
public enum Singleton4 {
    INSTANCE;

    private Singleton4() {

    }

    public static Singleton4 getInstance() {
        return INSTANCE;
    }
}
