package com.situ.web.dao.impl;

import com.situ.web.dao.IStudentDao;
import com.situ.web.pojo.Student;
import com.situ.web.pojo.vo.StudentBanjiVO;
import com.situ.web.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {
    @Override
    public List<StudentBanjiVO> selectAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<StudentBanjiVO> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT s.id,s.name,s.age,s.gender,b.name AS banjiName\n" +
                    "FROM student AS s LEFT JOIN banji AS b\n" +
                    "ON s.banji_id=b.id ORDER BY s.id";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {// 如果有下一个返回true，而且指向下一个
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String banjiName = resultSet.getString("banjiName");
                StudentBanjiVO studentBanjiVO = new StudentBanjiVO(id, name, age, gender, banjiName);
                list.add(studentBanjiVO);
            }
            for (StudentBanjiVO studentBanjiVO : list) {
                System.out.println(studentBanjiVO);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }
        return list;

    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from student where id=?";
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
    public void add(Student student) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into student(name,age,gender,banji_id) values(?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getGender());
            statement.setInt(4, student.getBanjiId());
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
    public Student selectById(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,gender from student where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                student = new Student(id, name, age, gender);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return student;
    }

    @Override
    public void update(Student student) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = " update student set name=?,age=?,gender=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getGender());
            statement.setInt(4, student.getId());
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
