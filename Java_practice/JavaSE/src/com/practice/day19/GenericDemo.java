package com.practice.day19;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericDemo {
    @Test
    public void test1(){
        List list = new ArrayList<>();
        list.add("张三");
        list.add(1);

        String str = (String) list.get(0);
    }

    @Test
    public void test2() {
        // 使用泛型指定List里面放的数据类型
        // 可以在编译时期进行类型检查，且可以避免频繁类型转化！
        List<String> list = new ArrayList();
        list.add("zhangsan");
        // 编译时候报错
        // list.add(1);

        // 获取时候返回的就是泛型里面写的类型，不需要进行类型转换
        String str = list.get(0);
    }

    @Test
    public void test3() {
        // 两端的数据类型必须要一致
        List<Object> list1 = new ArrayList<Object>();
        List<String> list2 = new ArrayList<String>();
        // 右侧的泛型可以不写
        List<String> list3 = new ArrayList<>();
        // 只在右侧写泛型不起作用
        List list4 = new ArrayList<String>();
        list4.add(1);

        // 编译时候报错
        // List<Object> list5 = new ArrayList<String>();
        // 泛型类型必须为引用数据类型
        // List<int> list6 = new ArrayList<>();
    }

    @Test
    public void test4() {
        List<String> list1 = new ArrayList<String>();  //创建泛型
        List<Integer> list2 = new ArrayList<Integer>();
        Class c1 = list1.getClass();  //获取泛型的类型
        Class c2 = list2.getClass();
        System.out.println(c1 == c2); //输出true
    }

//    public void save(List<Person> list) {
//        // list.add()
//    }
}
