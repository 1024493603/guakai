package com.practice.day19.thread;

//Java里面是单继承的，继承Thread类方式将单继承这个位置给占了
public class MyThread extends Thread{
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
