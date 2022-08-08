package com.practice.web.servlet;

import com.practice.web.pojo.Teacher;
import com.practice.web.util.JDBCUtil;
import com.practice.web.util.TeacherPageInfo;

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

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if(method == null || method.equals("")) {
            method = "selectByPage";
        }

        //加了过滤器则不需要再判断是否登陆
        /*HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Cannot forward after response has been committed
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        }*/

        switch (method) {
            case "selectByPage":
                selectByPage(req,resp);
                break;
            case "deleteById":
                deleteById(req,resp);
                break;
            case "add":
                add(req,resp);
                break;
            case "getUpdateTeacher":
                getUpdateTeacher(req,resp);
                break;
            case "update":
                update(req,resp);
                break;
            default:
                break;
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String address = req.getParameter("address");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update teacher set name=?,age=?,address=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, Integer.parseInt(age));
            preparedStatement.setString(3, address);
            preparedStatement.setInt(4, Integer.parseInt(id));
            System.out.println(preparedStatement);
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, null);
        }

        resp.sendRedirect(req.getContextPath() + "/teacher?method=selectByPage");
    }

    private void getUpdateTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select name,age,address from teacher where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
            System.out.println(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                teacher = new Teacher(Integer.parseInt(id),name,age,address);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }
        req.setAttribute("teacher", teacher);
        req.getRequestDispatcher("teacher_update.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String address = req.getParameter("address");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into teacher(name,age,address) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, Integer.parseInt(age));
            preparedStatement.setString(3, address);
            System.out.println(preparedStatement);
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, null);
        }

        resp.sendRedirect(req.getContextPath()+ "/teacher?method=selectByPage");
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from teacher where id = ?";
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

        resp.sendRedirect(req.getContextPath() + "/teacher?method=selectByPage");
    }

    //    http://localhost:8080/JavaWeb/teacher?method=selectByPage&pageNo=2&pageSize=5
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TeacherServlet.selectByPage");
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        if (pageNo == null || pageNo.equals("")) {
            pageNo = "1";
        }
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "5";
        }

        //封装PageInfo
        int pageN = Integer.parseInt(pageNo);
        int pageS = Integer.parseInt(pageSize);
        TeacherPageInfo pageInfo = new TeacherPageInfo();
        pageInfo.setPageNo(pageN);
        pageInfo.setPageSize(pageS);

        //分页涉及两条sql语句
        //1.查询当前页的数据
        int offset = (pageN - 1) * pageS;
        List<Teacher> list = selectPage(offset, pageS);
        pageInfo.setList(list);
        //2.查询总的数量
        int totalCount = selectTotalCount();
        //int totalPage = totalCount / pageS;
        int totalPage = (int)Math.ceil((double)totalCount / pageS);
        pageInfo.setTotalPage(totalPage);

        System.out.println(pageInfo);
        req.setAttribute("pageInfo",pageInfo);
        req.getRequestDispatcher("teacher_list.jsp").forward(req, resp);
    }

    private int selectTotalCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select count(*) from teacher";
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

    private List<Teacher> selectPage(int offset, int pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Teacher> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,address from teacher limit ?,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, pageSize);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                Teacher teacher = new Teacher(id, name, age, address);
                list.add(teacher);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }
        return list;
    }

}
