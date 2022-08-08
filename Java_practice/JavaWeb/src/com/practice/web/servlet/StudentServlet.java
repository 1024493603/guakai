package com.practice.web.servlet;

import com.practice.web.pojo.Student;
import com.practice.web.util.JDBCUtil;
import com.practice.web.util.StudentPageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//http://localhost:8080/JavaWeb/student
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    //访问servlet默认访问service方法
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //http://localhost:8080/JavaWeb/student?method=selectAll
        //http://localhost:8080/JavaWeb/student?method=deleteById&id=1
        //http://localhost:8080/JavaWeb/student?method=add
        //解决post请求乱码问题
        //req.setCharacterEncoding("UTF-8");

        //从网址上接受参数为req.getParameter
        String method = req.getParameter("method");

        if(method == null || method.equals("")) {
            method = "selectAll";
        }
        switch (method) {
            case "selectAll":
                selectAll(req,resp);
                break;
            case "deleteById" :
                deleteById(req,resp);
                break;
            case "add" :
                add(req,resp);
                break;
            case "getStudentUpdatePage" :
                getStudentUpdatePage(req,resp);
                break;
            case "update":
                update(req,resp);
                break;
        }

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = " update student set name=?,age=?,gender=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, Integer.parseInt(age));
            preparedStatement.setString(3, gender);
            preparedStatement.setInt(4, Integer.parseInt(id));
            System.out.println(preparedStatement);
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, null);
        }

        resp.sendRedirect(req.getContextPath() + "/student?method=selectAll");
    }

    private void getStudentUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,gender from student where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(id));
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                student = new Student(Integer.parseInt(id), name, age, gender);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }


        req.setAttribute("student",student);
        //只负责转发数据
        req.getRequestDispatcher("student_update.jsp").forward(req,resp);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from student where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
            System.out.println(preparedStatement);
            int count = preparedStatement.executeUpdate();
            System.out.println(count);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, null);
        }

        //重定向
        resp.sendRedirect(req.getContextPath() + "/student?method=selectAll");
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        if (pageNo == null || pageNo.equals("")) {
            pageNo = "1";
        }
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "5";
        }

        int pageN = Integer.parseInt(pageNo);
        int pageS = Integer.parseInt(pageSize);
        StudentPageInfo studentPageInfo = new StudentPageInfo();
        studentPageInfo.setPageNo(pageN);
        studentPageInfo.setPageSize(pageS);

        int page = (pageN - 1) * pageS;
        List<Student> list = selectPage(page, pageS);
        studentPageInfo.setList(list);

        int totalPage = (int)Math.ceil((double) getCountPage() / pageS);
        studentPageInfo.setTotalPage(totalPage);
        System.out.println(studentPageInfo);

        req.setAttribute("studentPageInfo", studentPageInfo);
        req.getRequestDispatcher("student_list.jsp").forward(req, resp);
    }

    private int getCountPage() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select count(*) from student";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    private List<Student> selectPage(int page, int pageS) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,gender from student limit ?,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, page);
            preparedStatement.setInt(2, pageS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String  name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Student student = new Student(id, name, age, gender);
                list.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }
        return list;
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentServlet.add");
        //form传回3个参数需要三个getParameter
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into student(name,age,gender) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, Integer.parseInt(age));
            preparedStatement.setString(3, gender);
            System.out.println(preparedStatement);
            int count = preparedStatement.executeUpdate();
            System.out.println(count);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, null);
        }

        //重定向
        resp.sendRedirect(req.getContextPath() + "/student?method=selectAll");
    }
}
