package com.practice.day19;

import org.junit.Test;

public class GenericDemo2 {
    //泛型方法
    public <K, T> K save (K k, T t) {
        return k;
    }

    @Test
    public void test1(){
        //使用时才确定泛型的类型
        Double k = save(1.0, 1);
    }
}
