package com.practice.day20.pattern.factory;

public class Sub extends Cal{
    @Override
    public double getResult() {
        return numA - numB;
    }
}
