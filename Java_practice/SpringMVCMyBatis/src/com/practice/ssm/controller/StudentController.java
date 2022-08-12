package com.practice.ssm.controller;

import com.practice.ssm.pojo.Student;
import com.practice.ssm.service.IStudentService;
import com.practice.ssm.service.Impl.StudentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private IStudentService studentService = new StudentServiceImpl();

    @RequestMapping("/selectAll")
    public void selectAll() {
        List<Student> studentList = studentService.selectAll();

    }
}
