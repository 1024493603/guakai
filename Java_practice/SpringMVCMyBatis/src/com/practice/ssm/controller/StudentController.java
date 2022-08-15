package com.practice.ssm.controller;

import com.practice.ssm.pojo.Student;
import com.practice.ssm.service.IStudentService;
import com.practice.ssm.service.Impl.StudentServiceImpl;
import com.practice.ssm.util.JSONResult;
import com.practice.ssm.util.JSONUtil;
import com.practice.ssm.util.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/deleteById")
    public String  deleteById(Integer id) {
        System.out.println("StudentController.deleteById");
        studentService.deleteById(id);
        return "redirect:/student/selectByPage.action";
    }

    @RequestMapping("/add")
    public void add(String name, Integer age, String gender, HttpServletResponse resp) {
        System.out.println("StudentController.add");
        Student student = new Student(name, age, gender);
        studentService.add(student);
        JSONUtil.toJson(resp, JSONResult.ok("添加成功"));
    }

    @RequestMapping("/selectById")
    public String selectById(Integer id, Model model) {
        System.out.println("StudentController.selectById");
        Student student = studentService.selectById(id);
        model.addAttribute(student);
        return "student_update";
    }

    @RequestMapping("/update")
    public void update(Student student, HttpServletResponse resp) {
        System.out.println("StudentController.update");
        studentService.update(student);
        JSONUtil.toJson(resp, JSONResult.ok("修改成功"));
    }
}
