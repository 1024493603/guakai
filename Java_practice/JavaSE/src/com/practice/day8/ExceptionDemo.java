package com.practice.day8;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExceptionDemo {
    @Test
    //test1,2为运行时异常，编译不会出错
    public void test1() {
        String str = "hello";
        char c1 = str.charAt(4);
        System.out.println(c1);
        //java.lang.StringIndexOutOfBoundsException字符串下标越界异常
        char c2 = str.charAt(5);
        System.out.println(c2);
    }

    @Test
    public  void test2() {
        //java.lang.ArithmeticException算数异常
        int i = 5;
        System.out.println(i / 0);
    }

    @Test
    //标记异常签名,但此处不处理异常再次抛出
    public void test3() throws ClassNotFoundException {
        //java.lang.ClassNotFoundException
        //编译时异常
        Class.forName("com.mysql.jdbc.Driver");
    }

    @Test
    //编译时异常
    //捕获异常,后面代码会继续执行
    public void test4() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //获得链接
            Connection connection = DriverManager.getConnection("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            //共用一个try，写自己的catch
            throwables.printStackTrace();
        } finally {
            //不管是否抛出异常，此处代码一定会执行
            System.out.println("ExceptionDemo.test4");
        }

        System.out.println();
    }

    @Test
    public void test5() {
        //java.io.FileNotFoundException未找到文件
        try {
            FileInputStream fileInputStream = new FileInputStream("a.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager(1000.0);
        accountManager.deposit(500.0);
        //使用时捕获异常，有问题则抛出，没问题则继续
        try {
            accountManager.withdraw(1600.0);
        } catch (NoMoneyException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }
}
