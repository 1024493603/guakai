package com.situ.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.situ.web.pojo.City;
import com.situ.web.pojo.Province;
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

@WebServlet("/ajax3")
public class Ajax3Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method) {
            case "selectProvince":
                selectProvince(req, resp);
                break;
            case "selectCity":
                selectCity(req, resp);
                break;
            default:
                System.out.println("Ajax3Servlet.default");
                break;
        }
    }

    private void selectCity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String provinceId = req.getParameter("provinceId");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<City> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,city,province_id from tm_city where province_id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(provinceId));
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String city = resultSet.getString("city");
                City ci = new City(id, city, Integer.parseInt(provinceId));
                list.add(ci);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.setContentType("text/html;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), list);
    }

    private void selectProvince(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Province> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,province from tm_province";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String province = resultSet.getString("province");
                Province provin = new Province(id, province);
                list.add(provin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

       /* resp.setContentType("text/html;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), list);*/
        JSONUtil.toJSON(resp, JSONResult.ok(list));
    }
}
