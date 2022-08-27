package com.practice.mall.controller;

import com.practice.mall.pojo.Cart;
import com.practice.mall.pojo.User;
import com.practice.mall.service.ICartService;
import com.practice.mall.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("/add")
    @ResponseBody
    public JSONResult add(Cart cart, HttpSession session) {
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        cart.setChecked(1);
        return JSONResult.ok("添加成功");
    }
}
