package com.practice.web.servlet;

import com.practice.web.pojo.User;
import com.practice.web.util.JDBCUtil;

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

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if(method == null || method.equals("")) {
            method = "login";
        }



        switch (method) {
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

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(); //获得当前会话的Session
        session.removeAttribute("user");    //清除当前会话的Session
        // session.invalidate();  //会全部销毁
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

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
        if (user == null) {  //用户名或者密码错误，即未查找到对应用户
            resp.sendRedirect(req.getContextPath() + "/fail.jsp");  //跳转到失败的界面
            System.out.println("UserServlet.login_null");
        } else {    //登陆成功转到首页
            HttpSession session = req.getSession(); //建立新的空Session
            session.setAttribute("user", user); //建立对应用户的Session，防止用户直接跳转网址
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }
}
