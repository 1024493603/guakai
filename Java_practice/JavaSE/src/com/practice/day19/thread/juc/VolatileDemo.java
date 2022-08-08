package com.practice.day19.thread.juc;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {
    //保证可见性
    //多线程使用同一个变量时，让该变量更改之后能被别的进程读到
    public static int num = 0;
    //private static volatile int num = 0;

    public static void main(String[] args) { // main
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (num==0){

                }
            }
        }).start();

        // Thread.sleep(1000);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);
    }
}
