package com.practice.day5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CollectionDemo {

    @Test
    public void test2(){
        int[] array = new int[3];
        ArrayList<Integer> list = new ArrayList<Integer>(); //有序，输入和输出顺序一致，可重复
        list.add(12);
        list.add(34);
        list.add(34);
        for (Integer integer : list) {
            System.out.println(integer);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
//        12
//        34
//        34


        System.out.println("--------------------------------------");
        HashSet<Integer> set = new HashSet<>();     //无序，输入和输出顺序不一定一致，不可重复
        set.add(12);
        set.add(34);
        set.add(45);
        set.add(45);
        for (Integer integer : set) {
            System.out.println(integer);
        }
//        34
//        12
//        45
    }

}
