package com.practice.day20.pattern.singleton;

// 懒汉式单例 - 内部类
public class Singleton5 {
    private  Singleton5() {}

    //没有用到这个静态内部类不会触发他的加载、连接、初始化，也就不会初始化对象
    private static class Holder {
        //线程安全
        //由JVM保证
        static Singleton5 INSTANCE = new Singleton5();
    }

    // 既保证了线程安全static静态保证，避免了双检锁的缺点，又保证懒汉特性，第一次访问才用到
    public  Singleton5 getINSTANCE() {
        return Holder.INSTANCE;
    }
}
