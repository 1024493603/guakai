package com.practice.day19.thread.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        // 多个线程同时访问同一个资源，把资源放入线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "a").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "b").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "c").start();
    }
}

// 资源类
class Ticket {
    private int ticketNum = 30;
    // 1、创建锁
    //false或者默认为不公平锁，可以插队
    //true为公平锁，先来后到
    private Lock lock = new ReentrantLock();
    /*lock.lock();
    try{

    } finally{
        lock.unclock();
    }*/

    public void sale() {
        // 2、加锁
        lock.lock();
        try {
            // 3、access the resource protected by the lock
            // 业务代码
            if (this.ticketNum > 0) {
                System.out.println(Thread.currentThread().getName() + "购得第" + ticketNum-- + "张票, 剩余" + ticketNum + "张票");
                // 增大出错的概率
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //4、解锁
            lock.unlock();
        }
    }
    //不公平锁时全为a买票
    //公平锁时abc循环买票
}
