package com.practice.mvc.controller;

import com.practice.mvc.pojo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

//@Component
@Controller
@RequestMapping("/student")
public class StudentController {

    //${pageContext.request.contextPath}/student/add.action
    @RequestMapping("/add1")
    public void add1(String name, Integer age, String gender) {
        System.out.println("StudentController.add");
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println("gender: " + gender);
        Student student = new Student(name, age, gender);
        System.out.println(student);
    }

    @RequestMapping("/add2")
    public ModelAndView add2(Student student) {
        System.out.println("StudentController.add1");
        System.out.println(student);
        // 转发数据到一个界面
        // Model:数据 View：界面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", student);
        modelAndView.setViewName("/student_info.jsp");
        return modelAndView;
    }

    // 默认情况下既支持Get请求也支持Post请求
    //方法不一样时会报405错误
    // @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.GET})
    // @RequestMapping(value = "/add", method = {RequestMethod.POST})
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Student student, Model model) {
        System.out.println("StudentController.add");
        System.out.println(student);

        model.addAttribute("student", student);

        return "/student_info.jsp";
    }

    @RequestMapping("/selectAll")
    public String selectAll(Model model) {
        System.out.println("StudentController.selectAll");
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "张三", 23, "男"));
        list.add(new Student(2, "王五", 23, "男"));
        list.add(new Student(3, "赵四", 23, "男"));
        model.addAttribute("list", list);
        return "/student_list.jsp";         //执行后返回一个界面
    }

    @RequestMapping("/deleteAll")
    public void delete(Integer[] ids) {
        System.out.println("StudentController.delete");
        for (Integer id : ids) {
            System.out.println(id);
        }
    }

    @RequestMapping("/deleteById")
    public String deleteById(Integer id) {
        System.out.println("StudentController.deleteById");
        System.out.println("id:" + id);

        return "redirect:/student/selectAll.action";    //重定向到另一个函数
    }

    @RequestMapping("/select")
    @ResponseBody //将返回的对象转换为json
    public List<Student> select(Model model) {
        System.out.println("StudentController.select");
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "张三", 23, "男"));
        list.add(new Student(2, "王五", 23, "男"));
        list.add(new Student(3, "赵四", 23, "男"));

        return list;
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public Student selectById() {
        System.out.println("StudentController.selectById");
        Student student = new Student(1, "李四", 24, "男");

        return student;
    }

    @RequestMapping("/selectByPage")
    @ResponseBody
    public void selectById(@RequestParam(value = "pageN", defaultValue = "1") Integer pageNo,//改名为pageN  原先pageNo变为新参数
                           @RequestParam(defaultValue = "5") Integer pageSize,//默认值为5
                           @RequestParam(required = true) Integer totalPage) {//该参数设定为必须传输的参数，否则为400错误
        /*if (pageNo == null) {
            pageNo = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }*/
        System.out.println("pageNo: " + pageNo);
        System.out.println("pageSize: " + pageSize);
        System.out.println("totalPage: " + totalPage);
    }
}
