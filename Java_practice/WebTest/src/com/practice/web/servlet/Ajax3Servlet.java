package com.practice.web.servlet;

import com.practice.web.pojo.Area;
import com.practice.web.pojo.City;
import com.practice.web.pojo.Province;
import com.practice.web.util.JDBCUtil;
import com.practice.web.util.JSONResult;
import com.practice.web.util.JSONUtil;

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
        System.out.println("Ajax3Servlet.com.practice.web.service");
        String method = req.getParameter("method");
        if(method == null || method.equals("")){
            method = "selectProvince";
        }
        switch (method) {
            case "selectProvince":
                selectProvince(req, resp);
                break;
            case "selectCity":
                selectCity(req, resp);
                break;
            case "selectArea":
                selectArea(req, resp);
                break;
            default:
                System.out.println("Ajax3Servlet.default");
                break;
        }
    }

    private void selectArea(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Ajax3Servlet.selectArea");
        String cityId = req.getParameter("cityId");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Area> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,area from tm_area where city_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(cityId));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String area = resultSet.getString("area");
                Area area1 = new Area(id, area, Integer.parseInt(cityId));
                list.add(area1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }

//        resp.setContentType("text/html;charset=utf-8");
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(resp.getWriter(), list);
        JSONUtil.toJson(resp, JSONResult.ok(list));
    }

    private void selectCity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Ajax3Servlet.selectCity");
        String province_id = req.getParameter("provinceId");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<City> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,city from tm_city where province_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(province_id));
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String city = resultSet.getString("city");
                City city1 = new City(id,city,Integer.parseInt(province_id));
                list.add(city1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }
        System.out.println(list);
//        resp.setContentType("text/html;charset=utf-8");
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(resp.getWriter(), list);
        JSONUtil.toJson(resp, JSONResult.ok(list));
    }

    private void selectProvince(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Ajax3Servlet.selectProvince");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Province> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,province from tm_province";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String province = resultSet.getString("province");
                Province province1 = new Province(id,province);
                list.add(province1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }
        System.out.println(list);

//        resp.setContentType("text/html;charset=utf-8");
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(resp.getWriter(), list);
        JSONUtil.toJson(resp, JSONResult.ok(list));
    }
}
