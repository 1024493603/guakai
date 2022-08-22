package com.situ.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.situ.web.pojo.Province;
import com.situ.web.pojo.vo.BanjiCount;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/echarts")
public class EChartsServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<BanjiCount> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT b.name,COUNT(*) AS value\n" +
                    "FROM student AS s INNER JOIN banji AS b \n" +
                    "ON s.banji_id=b.id\n" +
                    "GROUP BY b.id";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int value = resultSet.getInt("value");
                BanjiCount banjiCount = new BanjiCount(name, value);
                list.add(banjiCount);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.setContentType("text/html;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), list);
    }

    protected void service1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "java1807");
//        map.put("value", 23);

        List<Map<String, Object>> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT b.name,COUNT(*) AS value\n" +
                    "FROM student AS s INNER JOIN banji AS b \n" +
                    "ON s.banji_id=b.id\n" +
                    "GROUP BY b.id";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int value = resultSet.getInt("value");
                Map<String, Object> map = new HashMap<>();
                map.put("name", name);
                map.put("value", value);
                list.add(map);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.setContentType("text/html;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), list);

    }
}
