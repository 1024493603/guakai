package com.practice.day7;

public class ExtendsTheory {
    public static void main(String[] args) {
        Son son = new Son();//内存的布局
        /*
        加载顺序:Object -> Grandpa -> Father -> Son
        内存空间分配:
        继承关系在方法区中表示
        1.在堆中分配爷类,name、hobby的指针指向常量池的“大头爷爷”和“旅游”
        2.再分配父类,name指针指向常量池“大头爸爸”，age是基础类型所以直接存在堆中
        3.分配子类,name指向常量池“大头儿子”
        4.堆中这段首地址则分配给son变量存在栈中
        */
        System.out.println(son.name);
        /*
        此时按照查找关系来返回数据
        1.查看当前类是否有该属性
        2.如果当前类有且可以访问则返回信息
        3.如果当前类没有这个属性则查看父类
        4.如果父类没有则按照3继续查找父类的父类，直到Object
        */
    }
}

class Grandpa {
    String name = "大头爷爷";
    String hobby = "旅游";
}
class Father extends Grandpa {
    String name = "大头爸爸";
    int age = 39;
}
class Son extends Father {
    String name = "大头儿子";
}