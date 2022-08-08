package com.practice.day8.jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo {
    //原始写法
    @Test
    public void test1(){
        //初始化
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //1.加载驱动Class.forName
            Class.forName("com.mysql.jdbc.Driver");
            //2.获得连接对象Connection (使用java的connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?useUnicode=true&characterEncoding=UTF-8", "root", "1234");
            //3.写sql语句
            String sql = "select id,name,age,gender from student";
            //4.创建Statement
            statement = connection.createStatement();
            //5.执行sql语句 (将statement送过去
            resultSet = statement.executeQuery(sql);
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Student student = new Student(id,name,age,gender);
                list.add(student);
            }
            for (Student student : list) {
                System.out.println(student);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    //改善写法
    @Test
    public void test2(){
        //初始化
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,gender from student";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {// 如果有下一个返回true，而且指向下一个
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Student student = new Student(id, name, age, gender);
                list.add(student);
            }
            for (Student student : list) {
                System.out.println(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }
    }
    //使用PreparedStatement
    //优点：1.效率高 2.安全性好 3.语法不一样
    @Test
    public void test3(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,gender from student";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {// 如果有下一个返回true，而且指向下一个
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Student student = new Student(id, name, age, gender);
                list.add(student);
            }
            for (Student student : list) {
                System.out.println(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }
    }
    //更改数据库数据
    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into student(name,age,gender) values(?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "张三111");
            statement.setInt(2, 23);
            statement.setString(3, "男");
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }

    @Test
    public void testDelete(){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from student where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 9);
            System.out.println(statement);
            int count = statement.executeUpdate(sql);
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }

    @Test
    public void testUpdate(){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update student set name = ? , age = ? , gender = ? where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "李四");
            statement.setInt(2, 24);
            statement.setString(3, "女");
            statement.setInt(4, 10);
            System.out.println(statement);
            int count = statement.executeUpdate(sql);
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }
}
//异常继承
//sql过程
//三nf