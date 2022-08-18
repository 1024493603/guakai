package com.practice.springboot.controller;

import com.practice.springboot.pojo.User;
import com.practice.springboot.service.IUserService;
import com.practice.springboot.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/getLoginPage")
    public String getLoginPage() {
        return "/WEB-INF/login.jsp";
    }

    @RequestMapping("/login")
    @ResponseBody
    public JSONResult login(String name, String password, String code, HttpSession session) {
        String codeInSession = (String) session.getAttribute("codeInSession");
        System.out.println(codeInSession);
        if (!code.equalsIgnoreCase(codeInSession)) {
            return JSONResult.error("验证码错误");
        }

        User user = userService.login(name, password);

        if (user == null) {
            return JSONResult.error("登录失败");
        } else {
            session.setAttribute("user", user);
            return JSONResult.ok("登录成功");
        }
    }
}
