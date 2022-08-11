package com.practice.mvc.controller;

import com.practice.mvc.pojo.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Component
@Controller
@RequestMapping("/student")
public class StudentController {

    //${pageContext.request.contextPath}/student/add.action
    @RequestMapping("/add1")
    public void add(String name, Integer age, String gender) {
        System.out.println("StudentController.add");
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println("gender: " + gender);
        Student student = new Student(name, age, gender);
        System.out.println(student);
    }

    @RequestMapping("/add")
    public void add1(Student student) {
        System.out.println("StudentController.add1");
        System.out.println(student);
    }
}
