package com.practice.day19.thread.juc;

public class PCWithSynchronized {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        data.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Inc1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        data.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Inc2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        data.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Dec1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        data.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Dec2").start();

    }
}
//判断等待-->业务代码-->通知
//数字：资源类
class Data{
    //属性  只有0和1两个值
    private int number = 0;
    //+1方法  0->1
    //用if时 方法等待结束时已经判断完成不会再进行判断
    //改成while可以让方法唤醒时重新判断是否number==1
    public synchronized  void increment() throws InterruptedException {
        /*if*/while (number == 1){
            //等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"-->"+number);
        //加完了通知其他线程
        this.notifyAll();
    }

    //-1   1->0
    public synchronized void decrement() throws InterruptedException {
        /*if*/while (number == 0){
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"-->"+number);
        //减完了通知其他线程
        this.notifyAll();
    }
}
