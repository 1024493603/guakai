package com.situ.web.servlet;

import com.situ.web.pojo.User;
import com.situ.web.service.IUserService;
import com.situ.web.service.impl.UserServiceImpl;
import com.situ.web.util.JDBCUtil;
import com.situ.web.util.JSONResult;
import com.situ.web.util.JSONUtil;
import com.situ.web.util.LayUITableJSONResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if (method == null || method.equals("")) {
            method = "selectByPage";
        }
        switch (method) {
            case "selectByPage":
                selectByPage(req, resp);
                break;
            case "login":
                login(req, resp);
                break;
            case "logout":
                logout(req, resp);
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
            case "selectById":
                selectById(req, resp);
                break;
            default:
                break;
        }
    }

    private void selectById(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserServlet.selectById");
        String id = req.getParameter("id");
        User user = userService.selectById(Integer.parseInt(id));

        JSONUtil.toJSON(resp, JSONResult.ok(user));
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserServlet.update");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        String address = req.getParameter("address");
        String gender = req.getParameter("gender");
        User user = new User(Integer.parseInt(id), name, password, Integer.parseInt(age), address, gender);

        JSONResult jsonResult = userService.update(user);

        JSONUtil.toJSON(resp, jsonResult);
    }

    private void getUserUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserServlet.getUserUpdatePage");
        String id = req.getParameter("id");
        User user = userService.selectById(Integer.parseInt(id));
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
        JSONResult jsonResult = userService.add(user);
        JSONUtil.toJSON(resp, jsonResult);
    }

    private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserServlet.deleteAll");
        String[] ids = req.getParameterValues("ids[]");
        System.out.println(Arrays.toString(ids));
        JSONResult jsonResult = userService.deleteAll(ids);

        JSONUtil.toJSON(resp, jsonResult);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserServlet.deleteById");
        String id = req.getParameter("id");
        JSONResult jsonResult = userService.deleteById(Integer.parseInt(id));
        
        JSONUtil.toJSON(resp, jsonResult);
    }

    // /user?method=selectByPage&page=1&limit=10
    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserServlet.selectByPage");
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");

        LayUITableJSONResult layUITableJSONResult = userService.selectByPage(Integer.parseInt(page), Integer.parseInt(limit));

        JSONUtil.toJSON(resp, layUITableJSONResult);
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        // session.invalidate();
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
            JSONUtil.toJSON(resp, JSONResult.error("验证码错误"));
            return;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,password,age,address from users where name=? and password=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name1 = resultSet.getString("name");
                String password1 = resultSet.getString("password");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                String gender = resultSet.getString("gender");
                user = new User(id, name1, password1, age, address, gender);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /*if (user == null) {// 用户名密码错误
            //resp.sendRedirect(req.getContextPath() + "/fail.jsp");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {// 登录成功
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            // /localhost:8080/JavaWeb
            // /localhost:8080/JavaWeb/index.jsp
            resp.sendRedirect(req.getContextPath());
        }*/

        if (user == null) {
            JSONUtil.toJSON(resp, JSONResult.error("用户名或密码错误"));
        } else {
            session.setAttribute("user", user);
            JSONUtil.toJSON(resp, JSONResult.ok("登录成功"));
        }
    }
}
