package com.practice.mall.controller;

import com.practice.mall.pojo.User;
import com.practice.mall.service.impl.UserServiceImpl;
import com.practice.mall.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/checkUserLogin")
    @ResponseBody
    public JSONResult checkUserLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user == null ? JSONResult.error() : JSONResult.ok();
    }

    @RequestMapping("/login")
    public JSONResult login(String username, String password, HttpSession session) {
        User user = userServiceImpl.login(username, password);
        if (user == null) {
            return JSONResult.error();
        } else {
            session.setAttribute("user", user);
            return JSONResult.ok("登录成功");
        }
    }
}
