package com.practice.day19.thread.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();//创建MyCallable
        FutureTask<String> futureTask = new FutureTask<>(myCallable);//接收返回值
        Thread thread = new Thread(futureTask, "CallableDemo");//创建线程
        thread.start(); //启动线程
        String str = futureTask.get();//接收返回值
        System.out.println(str);
    }
}

class MyCallable implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println("MyCallable.call");
        return "abc";
    }
}