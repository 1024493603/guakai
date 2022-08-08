package com.practice.day19;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericDemo4 {
    public void save (List<?> list) {
        //只能遍历，不能修改
        for (Object o : list) {
            System.out.println(o);
        }
    }

    public void save2 (List<? extends Number> list) {
        //传过来的类必须是Number的子类
    }

    @Test
    public void testGeneric23() {
        List<Double> list1 = new ArrayList<Double>();
        List<Float> list2 = new ArrayList<Float>();
        List<Integer> list3 = new ArrayList<Integer>();

        List<String> list4 = new ArrayList<String>();

        // 调用
        save2(list1);
        save2(list2);
        save2(list3);
        //报错
        //save2(list4);
    }

    public void save3(List<? super String> list) {
        //传过来的类必须是String的父类
    }

    @Test
    public void testGeneric24() throws Exception {
        // 调用上面方法，必须传入String的父类
        List<Object> list1 = new ArrayList<Object>();
        List<String> list2 = new ArrayList<String>();

        List<Integer> list3 = new ArrayList<Integer>();

        save3(list1);
        save3(list2);
        //报错
        //save3(list3);
    }
}
