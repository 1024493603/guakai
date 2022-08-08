package com.practice.day19.thread.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        //未上锁：
        //MyCache myCache = new MyCache();
        //上了读写锁：
        MyCacheWithLock myCache = new MyCacheWithLock();
        //写入：
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myCache.write(temp + "", temp + "");
                }
            }, i + "").start();

        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myCache.read(temp + "");
                }
            }, String.valueOf(i)).start();
        }
    }
}

class MyCacheWithLock {
    private volatile Map<String, Object> map = new HashMap<>();

    //读写锁：对数据更精准控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //写数据：只希望有一个线程在执行
    public void write(String key, Object value) {
        // readWriteLock.writeLock().lock();
        Lock lock = readWriteLock.writeLock();
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // readWriteLock.writeLock().unlock();
            lock.unlock();
        }
    }

    //读数据：可一条或者多条同时执行
    public void read(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取数据：" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取数据完成-->" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}


/**
 * 未上锁：
 * 写入线程会被 读取线程中断，造成脏读，对数据不安全
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    //写数据：
    public void write(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "线程写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "线程写入完成！");
    }

    //读数据：
    public void read(String key) {
        System.out.println(Thread.currentThread().getName() + "线程读取数据：" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "线程读取数据完成-->" + o);
    }
}
