package com.situ.web.servlet;

import com.situ.web.pojo.Teacher;
import com.situ.web.service.ITeacherService;
import com.situ.web.service.impl.TeacherServiceImpl;
import com.situ.web.util.JDBCUtil;
import com.situ.web.util.JSONResult;
import com.situ.web.util.JSONUtil;
import com.situ.web.util.PageInfo;

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
    private ITeacherService teacherService = new TeacherServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if (method == null || method.equals("")) {
            method = "selectByPage";
        }

        /*HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Cannot forward after response has been committed
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }*/

        switch (method) {
            case "selectByPage":
                selectByPage(req, resp);
                break;
            case "deleteById":
                deleteById(req, resp);
                break;
            default:
                break;
        }
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("StudentServlet.deleteById");
        String id = req.getParameter("id");
        teacherService.deleteById(Integer.parseInt(id));

        /*Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "删除成功");*/

        // JSONResult jsonResult = new JSONResult(0, "删除成功");
        JSONUtil.toJSON(resp, JSONResult.ok("删除成功"));
    }


    // http://localhost:8080/JavaWeb/teacher?method=selectByPage&pageNo=2&pageSize=5
    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TeacherServlet.selectByPage");
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
        PageInfo pageInfo = teacherService.selectByPage(pageN, pageS);

        System.out.println(pageInfo);
        req.setAttribute("pageInfo", pageInfo);
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
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Teacher> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,address from teacher limit ?,?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, pageSize);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                Teacher teacher = new Teacher(id, name, age,address);
                list.add(teacher);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return list;
    }


}
