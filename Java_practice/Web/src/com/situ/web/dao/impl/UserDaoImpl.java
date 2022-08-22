package com.situ.web.dao.impl;

import com.situ.web.dao.IUserDao;
import com.situ.web.pojo.Teacher;
import com.situ.web.pojo.User;
import com.situ.web.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> selectPage(Integer offset, Integer limit) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,password,age,address,gender from users limit ?,?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                String gender = resultSet.getString("gender");
                User user = new User(id, name, password, age,address, gender);
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return list;
    }

    @Override
    public Integer selectTotalCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select count(*) from users";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return count;
    }

    @Override
    public void deleteById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from users where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }

    @Override
    public void add(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into users(name,password, age, address, gender) values(?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getGender());
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }

    @Override
    public User selectById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,password,age,address,gender from users where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                String gender = resultSet.getString("gender");
                user = new User(id, name, password, age, address, gender);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return user;
    }

    @Override
    public void update(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update users set name=?,password=?,age=?,gender=?,address=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getGender());
            statement.setString(5, user.getAddress());
            statement.setInt(6, user.getId());
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
    }
}
