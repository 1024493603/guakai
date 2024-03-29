package com.practice.springboot.controller;

import com.practice.springboot.pojo.Student;
import com.practice.springboot.service.IStudentService;
import com.practice.springboot.util.LayUITableJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @RequestMapping("/selectAll")
    @ResponseBody
    public List<Student> selectAll() {
        System.out.println("StudentController.selectAll");
        /*List<Student> list = new ArrayList<>();
        Student student1 = new Student(1, "zhangsan1", 23, "nan");
        Student student2 = new Student(2, "zhangsan2", 23, "nan");
        Student student3 = new Student(3, "zhangsan3", 23, "nan");
        list.add(student1);
        list.add(student2);
        list.add(student3);*/

        List<Student> list = studentService.selectAll();
        return list;
    }

    @RequestMapping("/selectByPage1")
    public String selectByPage1(Model model) {
        System.out.println("StudentController.selectByPage");
        List<Student> list = studentService.selectAll();
        model.addAttribute("list", list);
        return "/WEB-INF/student_list.jsp";
    }

    @RequestMapping("/getStudentListPage")
    public String getStudentListPage() {
        return "/WEB-INF/student_list.jsp";
    }

    @RequestMapping("/selectByPage")
    @ResponseBody
    public LayUITableJSONResult selectByPage(Integer page, Integer limit) {
        return studentService.selectByPage(page, limit);
    }
}
