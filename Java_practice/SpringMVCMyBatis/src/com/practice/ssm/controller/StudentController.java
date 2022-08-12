package com.practice.ssm.controller;

import com.practice.ssm.pojo.Student;
import com.practice.ssm.service.IStudentService;
import com.practice.ssm.service.Impl.StudentServiceImpl;
import com.practice.ssm.util.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student")
public class StudentController {
    private IStudentService studentService = new StudentServiceImpl();

    @RequestMapping("/selectByPage")
    public String selectByPage(Model model,
                            @RequestParam(defaultValue = "1") Integer pageNo,
                            @RequestParam(defaultValue = "4") Integer pageSize) {
        System.out.println("StudentController.selectByPage");
        PageInfo<Student> pageInfo = studentService.selectByPage(pageNo, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "student_list";
    }
}
