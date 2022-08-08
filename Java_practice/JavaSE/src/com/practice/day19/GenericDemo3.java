package com.practice.day19;

import com.practice.day4.Teacher;
import com.practice.day6.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericDemo3<T> {
    // Student Teacher
    public<K> void save(K k) {

    }

    public void update(T t) {

    }

    public void delete(T t) {

    }

    @Test
    public void test1() {
        //相当于用list  括号中的类型为这个类内方法统一使用的类型
        GenericDemo3<Student> genericDemo1 = new GenericDemo3<>();
        Student student = new Student();
        genericDemo1.update(student);
        genericDemo1.delete(student);

        GenericDemo3<Teacher> genericDemo2 = new GenericDemo3<>();
        Teacher teacher = new Teacher();
        genericDemo2.update(teacher);
        genericDemo2.delete(teacher);

        List<Student> list1 = new ArrayList<>();
        List<Teacher> list2 = new ArrayList<>();
    }
}
