package com.practice.day4.homework1;

public class Add extends Cal{

    public Add(int num1, int num2) {
        super(num1, num2);
    }

    @Override
    public int getResult() {
        //this 当前类对象
        //super 父类对象
        //this() new当前类构造方法
        //super() new父类构造方法
        //return super.getResult();
        return num1 + num2;
    }
}
