package com.practice.day18.reflect;

public class Oracle implements IDB{
    @Override
    public void getConnection() {
        System.out.println("Oracle.getConnection");
    }
}
