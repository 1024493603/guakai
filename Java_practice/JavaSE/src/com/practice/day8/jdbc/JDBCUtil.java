package com.practice.day8.jdbc;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    public static String URL;
    private static String USER;
    private static String PASSWORD;
    private static String DRIVER;

    //类加载时只执行一次
    static {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            FileInputStream fileInputStream = new FileInputStream("G:\\guakai-master\\Java_practice\\JavaSE\\src\\com\\practice\\day8\\jdbc\\db.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            URL = properties.getProperty("url");
            USER = properties.getProperty("user");
            PASSWORD = properties.getProperty("password");
            DRIVER = properties.getProperty("driver");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
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

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
