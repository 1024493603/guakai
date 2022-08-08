package com.practice.day18.reflect;

public class SqlServer implements IDB{
    @Override
    public void getConnection() {
        System.out.println("SqlServer.getConnection");
    }
}
