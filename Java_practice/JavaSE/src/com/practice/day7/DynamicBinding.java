package com.practice.day7;

public class DynamicBinding {
    public static void main(String[] args) {
        A a = new B();
        //不把B类中的sum注释时的结果
        System.out.println(a.sum()); //40
        System.out.println(a.sum1());//30

        //把B类中的sum和sum1注释后
        System.out.println(a.sum()); //30
        System.out.println(a.sum1());//20
    }
    //动态绑定机制
    //当调用对象方法时，该方法会和对象的运行类型绑定
    //当调用对象属性时，没有动态绑定机制，哪里声明哪里使用
}

class A {
    public int i = 10;
    public int sum() {
        return getI() + 10;
    }
    public int sum1() {
        return i + 10;
    }
    public int getI() {
        return i;
    }
}

class B extends A{
    public int i = 20;
//    public int sum() {
//        return i + 20;
//    }
    public int getI() {
        return i;
    }
//    public int sum1() {
//        return i + 10;
//    }
    public int sum2() {
        return i + 20;
    }
}
