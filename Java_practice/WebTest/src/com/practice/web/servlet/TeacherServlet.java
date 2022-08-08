package com.practice.web.servlet;

import com.practice.web.pojo.Teacher;
import com.practice.web.service.ITeacherService;
import com.practice.web.service.impl.TeacherServiceImpl;
import com.practice.web.util.JSONResult;
import com.practice.web.util.JSONUtil;
import com.practice.web.util.TeacherPageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
    private ITeacherService iTeacherService = new TeacherServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if(method == null || method.equals("")) {
            method = "selectByPage";
        }

        //加了过滤器则不需要再判断是否登陆
        /*HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Cannot forward after response has been committed
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        }*/

        switch (method) {
            case "selectByPage":
                selectByPage(req,resp);
                break;
            case "deleteById":
                deleteById(req,resp);
                break;
            case "add":
                add(req,resp);
                break;
            case "getUpdateTeacher":
                getUpdateTeacher(req,resp);
                break;
            case "update":
                update(req,resp);
                break;
            default:
                break;
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String address = req.getParameter("address");

        Teacher teacher = new Teacher(Integer.parseInt(id), name, Integer.parseInt(age), address);
        iTeacherService.update(teacher);

        resp.sendRedirect(req.getContextPath() + "/teacher?method=selectByPage");
    }

    private void getUpdateTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Teacher teacher = iTeacherService.getUpdateTeacher(Integer.parseInt(id));

        req.setAttribute("teacher", teacher);
        req.getRequestDispatcher("teacher_update.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String address = req.getParameter("address");

        iTeacherService.add(name, Integer.parseInt(age), address);

        resp.sendRedirect(req.getContextPath()+ "/teacher?method=selectByPage");
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        iTeacherService.deleteById(Integer.parseInt(id));

//        resp.sendRedirect(req.getContextPath() + "/teacher");
        JSONUtil.toJson(resp, JSONResult.ok("成功删除"));
    }

    //    http://localhost:8080/JavaWeb/teacher?method=selectByPage&pageNo=2&pageSize=5
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TeacherServlet.selectByPage");

        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        if (pageNo == null || pageNo.equals("")) {
            pageNo = "1";
        }
        if (pageSize == null || pageNo.equals("")) {
            pageSize = "5";
        }

        int pageN = Integer.parseInt(pageNo);
        int pageS = Integer.parseInt(pageSize);
        TeacherPageInfo teacherPageInfo = iTeacherService.selectByPage(pageN, pageS);
        System.out.println(teacherPageInfo);

        req.setAttribute("pageInfo", teacherPageInfo);
        req.getRequestDispatcher("teacher_list.jsp").forward(req, resp);
    }
}
