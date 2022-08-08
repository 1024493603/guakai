package com.practice.day19.thread;

import java.util.concurrent.*;

//自定义线程池创建
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                6,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );


        //当提交的任务数大于（workQueue.size() +maximumPoolSize ），就会触发线程池的拒绝策略。
        //10个顾客请求
        try {
            for (int i = 1; i <=10; i++) {
                final int index = i;
                //执行
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName()+" 为第"+index+"个客户办理业务");
                    }
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭
            threadPool.shutdown();
        }
    }
}