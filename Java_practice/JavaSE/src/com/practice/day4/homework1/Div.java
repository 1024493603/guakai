package com.practice.day4.homework1;

public class Div extends Cal{

    public Div(int num1, int num2) {
        super(num1, num2);
    }

    @Override
    public int getResult() {
        if (num2 == 0) {
            System.out.println("除数不能为零");
            return 0;
        } else {
            return num1 / num2;
        }
    }
}
