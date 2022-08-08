package com.practice.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ajax2")
public class Ajax2Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Ajax2Servlet.com.practice.web.service");
        String name = req.getParameter("name");
        // {'exist':true,'msg':'此用户已经存在，请更换一个'}
        // {'exist':false,'msg':'此用户可以使用'}
        Map<String, Object> map = new HashMap<>();
        if (name.equalsIgnoreCase("tom")) {
            map.put("exist", true);
            map.put("msg", "此用户已经存在，请更换一个");
        } else {
            map.put("exist", false);
            map.put("msg", "此用户可以使用");
        }

        resp.setContentType("text/html;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.writeValueAsString(map);  此方法为返回一个字符串
        // 把Map转换为json，同时借助response的流返回给浏览器
        objectMapper.writeValue(resp.getWriter(), map);
        //resp.getWriter()拿到一个IO流，之后将map传回去
    }
}