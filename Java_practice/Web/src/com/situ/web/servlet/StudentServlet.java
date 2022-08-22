package com.situ.web.servlet;

import com.situ.web.pojo.Student;
import com.situ.web.pojo.vo.StudentBanjiVO;
import com.situ.web.service.IStudentService;
import com.situ.web.service.impl.StudentServiceImpl;
import com.situ.web.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// JSP、Servlet都是浏览器可以访问服务器的资源
// http://localhost:8080/JavaWeb/student
// http://localhost:8080/JavaWeb/index.jsp
// url-pattern
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private IStudentService studentService = new StudentServiceImpl();

    // 访问servlet默认访问service方法
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("StudentServlet.service");
        // 解决post请求乱码问题
        // req.setCharacterEncoding("UTF-8");
        // http://localhost:8080/JavaWeb/student?method=selectAll
        // http://localhost:8080/JavaWeb/student?method=deleteById&id=1
        // http://localhost:8080/JavaWeb/student?method=add
        // http://localhost:8080/JavaWeb/index.jsp
        String method = req.getParameter("method");
        if (method == null || method.equals("")) {
            method = "selectAll";
        }
        switch (method) {
            case "selectAll":
                selectAll(req, resp);
                break;
            case "deleteById":
                deleteById(req, resp);
                break;
            case "add":
                add(req, resp);
                break;
            case "getStudentUpdatePage":
                getStudentUpdatePage(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
        }


    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentServlet.update");
        // update student set name=?,age=?,gender=? where id=16
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        Student student = new Student(Integer.parseInt(id), name, Integer.parseInt(age), gender);
        studentService.update(student);
        resp.sendRedirect(req.getContextPath() + "/student?method=selectAll");
    }

    private void getStudentUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StudentServlet.getStudentUpdatePage");
        String id = req.getParameter("id");
        Student student = studentService.selectById(Integer.parseInt(id));
        req.setAttribute("student", student);
        req.getRequestDispatcher("student_update.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentServlet.add");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String banjiId = req.getParameter("banjiId");
        // 1、Controller封装前台传递过来的数据
        Student student = new Student(name, Integer.parseInt(age), gender, Integer.parseInt(banjiId));
        // 2、Controller调用Service去做业务处理
        studentService.add(student);
        // 3、Controller在重定向到查找所有界面
        resp.sendRedirect(req.getContextPath() + "/student?method=selectAll");
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentServlet.deleteById");
        String id = req.getParameter("id");
        studentService.deleteById(Integer.parseInt(id));
        // 重定向 direction
        resp.sendRedirect(req.getContextPath() + "/student?method=selectAll");
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StudentServlet.selectAll");

        List<StudentBanjiVO> list = studentService.selectAll();
        // 把List集合中数据放到request内存里面
        req.setAttribute("list", list);
        // 转发到student_list.jsp页面进行展示
        req.getRequestDispatcher("student_list.jsp").forward(req, resp);
    }
}
