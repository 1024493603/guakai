package com.practice.day19.thread;

public class Something {
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    private Object lock3 = new Object();

    public void lockMethod1() {
        synchronized (lock1) {

        }
    }

    public void lockMethod2() {
        synchronized (lock1) {

        }
    }

    public void lockMethod3() {
        synchronized (lock2) {

        }
    }

    // this
    public synchronized void method1() {

    }

    public void method2() {
        synchronized (this) {

        }
        // ...
    }

    // Something.class
    public static synchronized void staticMethod1() {

    }

    public static synchronized void staticMethod2() {
        synchronized (Something.class) {

        }
    }
}
