package com.practice.day18.reflect;

public class MySql implements IDB{
    @Override
    public void getConnection() {
        System.out.println("MySql.getConnection");
    }
}
