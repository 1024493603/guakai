package com.practice.day20.pattern.singleton;

public class Singleton3 {
    //可见性  不保证原子性  禁止指令重排
    private volatile static Singleton3 INSTANCE;

    private Singleton3() {}

    //双重检查
    public static Singleton3 getINSTANCE() {
        if (INSTANCE == null) {
            //只在第一次会出现问题时加锁
            synchronized (Singleton3.class) {
                if (INSTANCE == null) {
                   INSTANCE = new Singleton3();
                }
            }
        }
        return INSTANCE;
    }
}
