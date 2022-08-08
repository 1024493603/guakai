package com.practice.day19.thread.juc;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        //如果是2020就改成2021
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
        //如果和期望的值相同，就更新这个值，否则就不更新
        System.out.println(atomicInteger.compareAndSet(2020, 2022));
        System.out.println(atomicInteger.get());
    }

    @Test
    public void test1() {
        int num = 0;
        num++;
        // CAS
        AtomicInteger i = new AtomicInteger(0);
        int i1 = i.getAndIncrement();// ++i  i++
        System.out.println(i1);
    }
}
