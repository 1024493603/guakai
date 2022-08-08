package com.practice.day4.homework1;

public class Multi extends Cal{

    public Multi(int num1, int num2) {
        super(num1, num2);
    }

    @Override
    public int getResult() {
        return num1 * num2;
    }
}