package com.practice.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.web.pojo.vo.BanjiCount;
import com.practice.web.util.JDBCUtil;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/echars")
public class ECharsServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BanjiCount> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select b.name,count(*) as value " +
                    "from student as s inner join banji as b " +
                    "on s.banji_id=b.id " +
                    "group by b.id";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int value = resultSet.getInt("value");
                BanjiCount banjiCount = new BanjiCount(name, value);
                list.add(banjiCount);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }
        System.out.println(list);
        System.out.println("ECharsServlet.com.practice.web.service");
        resp.setContentType("text/html;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), list);
    }

    protected void service1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Map<String ,Object>> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select b.name,count(*) as value " +
                         "from student as s inner join banji as b " +
                         "on s.banji_id=b.id " +
                         "group by b.id";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int value = resultSet.getInt("value");
                Map<String ,Object> map = new HashMap<>();
                map.put("name", name);
                map.put("value", value);
                list.add(map);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }
        System.out.println(list);
        resp.setContentType("text/html;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), list);
    }
}
