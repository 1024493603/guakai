package com.practice.day4;

//学生和老师共有属性
//用protect保证子类访问但外部不可
//Object是所有类的父类，不一定是直接父类
public class Person {
    protected int id;
    protected String name;
    protected int tall;
    protected int weight;

    public Person() {
        //super();
    }

    public Person(int id, String name, int tall, int weight) {
        this.id = id;
        this.name = name;
        this.tall = tall;
        this.weight = weight;
    }
}