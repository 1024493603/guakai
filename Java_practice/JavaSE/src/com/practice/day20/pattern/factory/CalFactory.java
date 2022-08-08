package com.practice.day20.pattern.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


//用反射和配置文件实现简单工厂
public class CalFactory {
    public static Cal createCal() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        FileInputStream fileInputStream = new FileInputStream("JavaSE/src/com/practice/day20/pattern/factory/cal.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        String className = properties.getProperty("className");
        Class clazz = Class.forName(className);

        Cal cal = (Cal)clazz.newInstance();
        return cal;
    }

    // 简单工厂是：选择new哪个子类对象
    public static Cal createCal1(String calType) {
        // 从配置文件里面读取到底new哪个

        Cal cal = null;
        switch (calType) {
            case "+" :
                cal = new Add();
                break;
            case "-" :
                cal = new Sub();
                break;
        }

        return cal;
    }
}