package com.practice.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//cookie是保存到浏览器的技术
@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("goods", "IPhone");
        Cookie cookie1 = new Cookie("name", "Ipad");
        resp.addCookie(cookie);
        resp.addCookie(cookie1);
    }
}
