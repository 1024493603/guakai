package com.practice.web.servlet;

import com.practice.web.pojo.User;
import com.practice.web.service.IUserService;
import com.practice.web.service.impl.UserServiceImpl;
import com.practice.web.util.JDBCUtil;
import com.practice.web.util.JSONResult;
import com.practice.web.util.JSONUtil;
import com.practice.web.util.LayUITableJSONResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private IUserService iUserService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if(method == null || method.equals("")) {
            method = "selectByPage";
        }

        switch (method) {
            case "selectByPage" :
                selectByPage(req,resp);
                break;
            case "selectById":
                selectById(req, resp);
                break;
            case "deleteById":
                deleteById(req, resp);
                break;
            case "deleteAll":
                deleteAll(req, resp);
                break;
            case "add":
                add(req, resp);
                break;
            case "getUserUpdatePage":
                getUserUpdatePage(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "login":
                login(req,resp);
                break;
            case "logout":
                logout(req, resp);
                break;
            default:
                break;
        }
    }

    private void selectById(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserServlet.selectById");
        String id = req.getParameter("id");
        User user = iUserService.selectById(Integer.parseInt(id));

        JSONUtil.toJson(resp, JSONResult.ok(user));
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        String address = req.getParameter("address");
        String gender = req.getParameter("gender");

        User user = new User(Integer.parseInt(id), name, password, Integer.parseInt(age), address, gender);
        JSONResult jsonResult = iUserService.update(user);
        JSONUtil.toJson(resp, jsonResult);

    }

    private void getUserUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserServlet.getUserUpdatePage");
        String id = req.getParameter("id");

        User user = iUserService.selectById(Integer.parseInt(id));

        req.setAttribute("user", user);
        req.getRequestDispatcher("user_update.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserServlet.add");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        String address = req.getParameter("address");
        String gender = req.getParameter("gender");
        User user = new User(name, password, Integer.parseInt(age), address, gender);

        JSONResult jsonResult = iUserService.add(user);
        JSONUtil.toJson(resp, jsonResult);
    }

    private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserServlet.deleteAll");
        String[] ids = req.getParameterValues("ids[]");   //返回数组

        JSONResult jsonResult = iUserService.deleteAll(ids);
        JSONUtil.toJson(resp, jsonResult);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserServlet.deleteById");
        String id = req.getParameter("id");
        JSONResult jsonResult = iUserService.deleteById(Integer.parseInt(id));

        JSONUtil.toJson(resp, jsonResult);
    }

    // /user?method=selectByPage&page=1&limit=10
    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserServlet.selectByPage");
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");

        LayUITableJSONResult layUITableJSONResult = iUserService.selectByPage(Integer.parseInt(page), Integer.parseInt(limit));
        System.out.println(layUITableJSONResult);
        JSONUtil.toJson(resp, layUITableJSONResult);
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        // session.invalidate();  //会全部销毁
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String code = req.getParameter("code");

        // 先判断验证码是不是正确
        // 验证码错误，返回一个错误提示信息
        // 验证码正确，再验证用户名和密码是否正确
        HttpSession session = req.getSession();
        String codeInSession = (String) session.getAttribute("codeInSession");
        if (!code.equalsIgnoreCase(codeInSession)) {
            JSONUtil.toJson(resp, JSONResult.error("验证码错误"));
            return;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,password from users where name=? and password=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name1 = resultSet.getString("name");
                String password1= resultSet.getString("password");
                user = new User(id, name1, password1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }
//        if (user == null) {  //用户名或者密码错误，即未查找到对应用户
//            resp.sendRedirect(req.getContextPath() + "/fail.jsp");  //跳转到失败的界面
//            System.out.println("UserServlet.login_null");
//        } else {    //登陆成功转到首页
//            HttpSession session = req.getSession();
//            session.setAttribute("user", user); //建立对应用户的Session，防止用户直接跳转网址
//            resp.sendRedirect(req.getContextPath() + "/index.jsp");
//        }

        if (user == null) {
            JSONUtil.toJson(resp, JSONResult.error("用户名或密码错误"));
        } else {
            //HttpSession session = req.getSession();
            session.setAttribute("user", user);
            JSONUtil.toJson(resp, JSONResult.ok("登录成功"));
        }
    }
}
