package com.practice.day19.thread.producerconsumer;

import java.util.Random;

public class ConsumerThread extends Thread{
    private Panzi panzi;

    public ConsumerThread(String name, Panzi panzi) {
        super(name);
        this.panzi = panzi;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            Cake cake = panzi.getCake();
            System.out.println(Thread.currentThread().getName() + " : " + cake);

            // 等待的时间是随机的，模拟吃蛋糕时间
            try {
                Thread.sleep(new Random().nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
