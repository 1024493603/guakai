package com.practice.day5;

public class Person {
    private static User1 user1 = new User1();
    private User2 user2 = new User2();

    //静态代码块
    //在类加载的时候执行，只会执行一次
    static {
        System.out.println("Person.static 初始值设定项");
    }

    //实例初始化块，每次调用构造方法之前调用
    {
        System.out.println("Person.实例化初始器");
    }

    public Person() {
        System.out.println("Person.Person");
    }
}
