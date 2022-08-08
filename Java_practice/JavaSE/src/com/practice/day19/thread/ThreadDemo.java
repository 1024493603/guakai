package com.practice.day19.thread;

public class ThreadDemo {

    public static void main(String[] args) {
    // 匿名内部类
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        }
    });
        thread.start();

        //常用创建线程方法
        new Thread(new Runnable() {
        @Override
        public void run() {

        }
    }).start();
}

    public static void main3(String[] args) {

        MyRunnable myRunnable = new MyRunnable();
        //给线程命名
        Thread thread = new Thread(myRunnable, "myRunnable");
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadDemo.main3");
    }

    public static void main2(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        MyThread myThread = new MyThread();
        //这不是启动一个线程，这是调用对象里面一个普通方法run()方法
        myThread.run();
        //真正启动一个线程调用start()方法，执行的代码就是run()方法里面代码
        myThread.start();
    }

    public static void main1(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());// main
        System.out.println(Thread.currentThread().getName());

        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }

}
