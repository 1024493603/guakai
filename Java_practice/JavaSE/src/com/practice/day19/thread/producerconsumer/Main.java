package com.practice.day19.thread.producerconsumer;

public class Main {
    public static void main(String[] args) {
        Panzi panzi = new Panzi();
        ProducerThread producerThread = new ProducerThread("生产者线程", panzi);
        producerThread.start();
        ConsumerThread consumerThread = new ConsumerThread("消费者线程", panzi);
        consumerThread.start();
    }
}
