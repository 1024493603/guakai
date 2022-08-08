package com.practice.day7;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestArrayList {
    public static void main(String[] args) {
        System.out.println(arrayListGrowRule(30));
        testAddAllGrowEmpty();
        testAddAllGrowNotEmpty();
    }

    private static List<Integer> arrayListGrowRule(int n) {
        List<Integer> list = new ArrayList<>();
        int init = 0;
        list.add(init);
        if (n >= 1) {
            init = 10;
            list.add(init);
        }
        for (int i = 1; i < n; i++) {
            init += (init) >> 1;
            list.add(init);
        }
        return list;
    }

    // addAll(Collection c) 没有元素时，扩容为 Math.max(10, 实际元素个数)，
    // 有元素时为 Math.max(原容量 1.5 倍, 实际元素个数)
    private static void testAddAllGrowEmpty() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));// //max(10,3) = 10
        //list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));// 11
        System.out.println(length(list));
    }

    // addAll(Collection c) 没有元素时，扩容为 Math.max(10, 实际元素个数)，
    // 有元素时为 Math.max(原容量 1.5 倍, 实际元素个数)
    private static void testAddAllGrowNotEmpty() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }// list里面已经有10个元素
        //list.addAll(Arrays.asList(1, 2, 3));// max(15,10)=15
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));// max(15,16)=16
        System.out.println(length(list));
    }

    public static int length(ArrayList<Integer> list) {
        try {
            Field field = ArrayList.class.getDeclaredField("elementData");
            field.setAccessible(true);
            return ((Object[]) field.get(list)).length;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
