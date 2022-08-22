package com.situ.web.servlet;

import com.situ.web.pojo.Banji;
import com.situ.web.pojo.vo.StudentBanjiVO;
import com.situ.web.util.JDBCUtil;
import com.situ.web.util.JSONResult;
import com.situ.web.util.JSONUtil;

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

@WebServlet("/banji")
public class BanjiServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null || method.equals("")) {
            method = "selectAll";
        }
        switch (method) {
            case "selectAll":
                selectAll(req, resp);
                break;
        }
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Banji> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name FROM banji";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {// 如果有下一个返回true，而且指向下一个
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Banji banji = new Banji(id, name);
                list.add(banji);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        JSONUtil.toJSON(resp, JSONResult.ok(list));
    }
}
