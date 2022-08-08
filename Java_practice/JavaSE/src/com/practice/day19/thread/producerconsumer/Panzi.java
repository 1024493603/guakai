package com.practice.day19.thread.producerconsumer;

import java.util.LinkedList;

//核心的资源类
//extends Object
public class Panzi {
    //模拟队列
    private LinkedList<Cake> list = new LinkedList();

    //假定盘子无限大    如果限定盘子只有10个
    public synchronized void putCake(Cake cake) {
        if (list.size() >= 1) {
            try {
                System.out.println("生产者线程 putCake wait");
                //this.wait();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.addLast(cake);
        //this.notifyAll();
        notifyAll();    //唤醒停在wait()的线程 (V操作)
    }

    public synchronized Cake getCake() {
        if (list.size() <= 0) { //没有蛋糕，需要等待
            try {
                System.out.println("消费者线程 getCake wait");
                wait();     //请求(P操作)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Cake cake = list.removeFirst();
        notifyAll();
        return cake;
    }
}
