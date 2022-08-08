package com.practice.day5;

public class Student extends Person{
    private static User3 user3 = new User3();
    private  User4 user4 = new User4();

    static {
        System.out.println("Student.static 初始值设定项");
    }

    {
        System.out.println("Student.实例化初始器");
    }

    public Student() {
        super();
        System.out.println("Student.Student");
    }
}
