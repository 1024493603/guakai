package com.practice.day19.annotation;

import org.junit.Test;

import java.lang.reflect.Method;

@SuppressWarnings({"unused"})
@Author(name = "李四", age = 10, value = "/auth")
public class AuthorDemo {

    @Test
    @Author(name = "张三", age = 24, value = "男")
    public void save() throws NoSuchMethodException {
        Class clazz = AuthorDemo.class;                     //拿到字节码
        Method method = clazz.getMethod("save");      //拿到方法
        Author author = method.getAnnotation(Author.class); //拿到注解
        System.out.println(author.name());                  //输出
        System.out.println(author.age());
        System.out.println(author.value());
    }

    @Test
    public void test1(){
        Class<AuthorDemo> clazz = AuthorDemo.class;
        Author annotation = clazz.getAnnotation(Author.class);
        System.out.println(annotation.value());
        System.out.println(annotation.name());
        System.out.println(annotation.age());
    }

}
