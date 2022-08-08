package com.practice.day20.pattern.factory;

import org.junit.Test;

import java.io.IOException;

public class FactoryDemo {
    @Test
    public void test1() {
        Cal cal = new Add();
        cal.setNumA(3);
        cal.setNumB(5);
        double result1 = cal.getResult();
        System.out.println(result1);

        cal = new Sub();
        double result2 = cal.getResult();
    }

    //反射和线程时用main函数
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        Cal cal = CalFactory.createCal();
        cal.setNumA(3);
        cal.setNumB(5);
        double result = cal.getResult();
        System.out.println(result);
    }

}
