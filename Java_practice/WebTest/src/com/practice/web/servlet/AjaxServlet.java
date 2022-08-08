package com.practice.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {
//    @Override
//    protected void com.practice.web.service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }

    //get过来的会经过doGet,post过来的会经过doPost,两者都会经过service

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AjaxServlet.doGet");
        // {'name': '张三'}
        String name = req.getParameter("name"); //取得数据
        System.out.println(name);
        // {"name": "李四","age":23}
        resp.setContentType("text/html;charset=utf-8");//解决乱码
        resp.getWriter().write("{\"name\": \"李四\",\"age\":23}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AjaxServlet.doPost");
        // req.setCharacterEncoding("UTF-8");

        //模拟后台需要进行3000毫秒操作
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //代码跟get一样
        // {'name': '张三'}
        String name = req.getParameter("name");
        System.out.println(name);
        // {"name": "李四","age":23}
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("{\"name\": \"李四11\",\"age\":24}");

        // doGet(req, resp);
    }
}
