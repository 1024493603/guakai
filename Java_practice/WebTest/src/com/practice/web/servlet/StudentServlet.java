package com.practice.web.servlet;

import com.practice.web.pojo.Student;
import com.practice.web.service.IStudentService;
import com.practice.web.service.impl.StudentServiceImpl;
import com.practice.web.util.JSONResult;
import com.practice.web.util.JSONUtil;
import com.practice.web.util.StudentPageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    //声明接口赋值实现类
    private IStudentService studentService = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");

        if(method == null || method.equals("")) {
            method = "selectAll";
        }
        switch (method) {
            case "selectAll":
                selectAll(req,resp);
                break;
            case "deleteById" :
                deleteById(req,resp);
                break;
            case "add" :
                add(req,resp);
                break;
            case "getStudentUpdatePage" :
                getStudentUpdateInfo(req,resp);
                break;
            case "update":
                update(req,resp);
                break;
        }

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");

        Student student = new Student(Integer.parseInt(id), name, Integer.parseInt(age), gender);
        studentService.update(student);

        //resp.sendRedirect(req.getContextPath() + "/student?method=selectAll");
        JSONUtil.toJson(resp, JSONResult.ok("编辑成功"));
    }

    private void getStudentUpdateInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Student student = studentService.getStudentUpdateInfo(Integer.parseInt(id));

        req.setAttribute("student",student);
        //只负责转发数据
        req.getRequestDispatcher("student_update.jsp").forward(req,resp);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        studentService.deleteById(id);

//        重定向
//        resp.sendRedirect(req.getContextPath() + "/student?method=selectAll");

        /*Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "删除成功");*/

        // JSONResult jsonResult = new JSONResult(0, "删除成功");
        JSONUtil.toJson(resp, JSONResult.ok("删除成功。"));
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //List<Student> students = studentService.selectAll();
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        if (pageNo == null || pageNo.equals("")) {
            pageNo = "1";
        }
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "5";
        }

        int pageN = Integer.parseInt(pageNo);
        int pageS = Integer.parseInt(pageSize);
        StudentPageInfo studentPageInfo = studentService.getStudentPageInfo(pageN, pageS);
        System.out.println(studentPageInfo);

        req.setAttribute("pageInfo", studentPageInfo);
        req.getRequestDispatcher("student_list.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentServlet.add");
        //form传回3个参数需要三个getParameter
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");

        studentService.add(name, age, gender);

//        //重定向
//        resp.sendRedirect(req.getContextPath() + "/student?method=selectAll");
        JSONUtil.toJson(resp, JSONResult.ok("添加成功"));
    }
}
