package com.practice.day18.reflect;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class DBDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //IDB db = new MySql();
        //db.getConnection();
        //db = new Oracle(); //多态
        // 代码中不出现具体的子类代码
        // 反射+配置文件
        //1.读取配置文件
        FileInputStream fileInputStream = new FileInputStream("JavaSE/src/com/practice/day18/reflect/db.properties");
        Properties properties = new Properties();
        //2.接收配置文件
        properties.load(fileInputStream);
        //3.获得配置文件中内容
        String className = properties.getProperty("className");
        //4.声明反射
        Class clazz = Class.forName(className);
        //5.获取无参构造方法
        Constructor constructor = clazz.getConstructor();
        //6.运用构造方法定义类
        IDB db = (IDB) constructor.newInstance();
        db.getConnection();

        // 如果调用的是无参构造方法，可以不需要获得Constructor，直接clazz.newInstance();
    }
}
