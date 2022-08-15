package com.practice.spring.controller;

import com.practice.spring.service.ICourseService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/*<bean name="studentController" class="com.situ.spring.controller.StudentController">
    <property name="studentService" ref="studentService"/>
</bean>*/
@Controller("courseController")
//@Controller @Service @Repository 本身没有区别，都是new一个对象放到Spring容器中
//@Component new一个对象放到Spring容器中,不起名字，放到容器中默认的名字是类名收个单词首字母小写
public class CourseController {

    //  <property name="studentService" ref="studentService"/>
    @Resource(name = "courseService")
    private ICourseService courseService;

    public  void selectAll() {
        System.out.println("CourseController.selectAll");
        courseService.selectAll();
    }
}