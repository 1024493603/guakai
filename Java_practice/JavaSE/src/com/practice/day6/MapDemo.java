package com.practice.day6;

import org.junit.Test;

import java.util.*;

public class MapDemo {
    @Test
    public void test1() {
        //Map为HashMap的父类，声明父类赋值子类对象
        Map<String, String> map = new HashMap<>();
        map.put("cn", "中");
        map.put("un", "美");
        //将map存到set里面
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        //遍历
        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key:" + key + " value:" + value);
        }
        System.out.println();
        //根据Key拿出对应Value
        String value = map.get("cn");
        System.out.println(value);
        System.out.println();

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            String val = map.get(key);
            System.out.println("key:" + key + " val:" + val);
        }
    }

    @Test
    public void test2() {
        Map<String, String> map = new HashMap<>();
        map.put("cn", "中");
        map.put("un", "美");
        //迭代器遍历
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) { //只判断下一个有没有值
            String key = iter.next();
            String value = map.get(key);
            System.out.println("key: " + key + ", value: " + value);
        }
    }

    @Test
    public void test3() {
        Collection<String> collection = new ArrayList<>();
        collection.add("张三");
        collection.add("李四");
        collection.add("王五");
        collection.add("张三");
        System.out.println(collection);
        collection.remove("张三");
        System.out.println(collection);
        Object[] array = collection.toArray();
        System.out.println("---------------------------");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        System.out.println();
        //迭代器迭代Collection
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {//判断有没有下一个,有下一个返回true
            String data = iterator.next();//用来返回迭代的下一个元素，并把指针向后移动一位
            System.out.println(data);
        }
    }

    @Test
    public void test4() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        //报错java.com.practice.com.practice.com.practice.com.practice.web.com.practice.com.practice.web.util.ConcurrentModificationException
        //遍历的过程中用集合的方法修改了集合的长度
//        for (String item : list) {
//            if (item.equals("3")) {
//                System.out.println(item);
//                list.remove(item);
//            }
//        }
//        System.out.println(list.size());

        //遍历的时候移除使用Iterator里面的remove方法。
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String data = (String) iterator.next();
            if (data.equals("3")) {
                // 不管在foreach还是Iterator遍历过程中都不允许使用list改变长度
                // 如果是移除元素，可以使用Iterator里面的remove，但是对于添加
                // Iterator就没有提供，可以参考ListIterator
                iterator.remove();
                System.out.println(data);
            }
        }
        System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    public void test5() {
        TreeSet<Student> treeSet = new TreeSet<>();
        treeSet.add(new Student(1, "张三", 23));
        treeSet.add(new Student(2, "李四", 13));
        treeSet.add(new Student(3, "周七", 13));
        treeSet.add(new Student(4, "王五", 43));
        treeSet.add(new Student(5, "赵六", 33));

        //java.lang.ClassCastException
        //需要指定用什么元素进行排序
        System.out.println(treeSet);
        //解决方法：在自定义类中实现"Comparable"接口，并重写接口中的compareTo方法

    }
}
