package com.practice.day4;

public class Teacher extends Person{
    private String className;

    public Teacher() {
        //此处有个默认的super();
        //调用父类无参构造方法
        //super();
    }

    //构造子类时要先构造父类
    public Teacher(int id, String name, int tall, int weight, String className) {
        // new Person(int id, String name, int tall, int weight)
        //super()必须放在第一行
        super(id, name, tall, weight);
        this.className = className;
    }
}