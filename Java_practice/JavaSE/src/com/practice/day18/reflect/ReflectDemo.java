package com.practice.day18.reflect;

import com.practice.day18.Student;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {

    //获得字节码对象
    @Test
    public void test1() throws ClassNotFoundException {
        //1.Class.forName("类路径")
        Class clazz1 = Class.forName("com.practice.day18.Student");
        //2.类型.class
        Class clazz2 = Student.class;
        //3.对象.getClass()
        Student student = new Student();
        Class clazz3 = student.getClass();

        System.out.println(clazz1 == clazz2);//true
        System.out.println(clazz1 == clazz3);//true
        System.out.println(clazz2 == clazz3);//true
    }

    @Test
    public void testConstructor(){
        Class clazz1 = Student.class;
        Constructor[] constructors = clazz1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
    }

    @Test
    public void testConstructor2(){
        Class clazz1 = Student.class;
        Constructor[] constructors = clazz1.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
            System.out.println(constructor.getName());
            System.out.println(constructor.getModifiers());
        }
    }

    @Test
    public void testConstructor3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //Student student = new Student(1, "张三");
        Class clazz1 = Student.class;
        Constructor constructor = clazz1.getDeclaredConstructor(Integer.class, String.class);
        //java.lang.IllegalAccessException
        constructor.setAccessible(true);    //可以访问私有类
        Student student = (Student) constructor.newInstance(1, "张三");
        System.out.println(student);
    }

    @Test
    public void testMethod() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //Student student = new Student();
        //student.setName("lisi");

        Class clazz = Student.class;
        Student student = (Student) clazz.newInstance();//创建该类(无参方法)
        Method method = clazz.getMethod("setName", String.class);
        method.invoke(student, "李四");
        System.out.println(student);
    }
}
