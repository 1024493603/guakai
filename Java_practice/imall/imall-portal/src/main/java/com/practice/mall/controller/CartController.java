package com.practice.mall.controller;

import com.practice.mall.pojo.Cart;
import com.practice.mall.pojo.User;
import com.practice.mall.pojo.vo.CartVO;
import com.practice.mall.service.ICartService;
import com.practice.mall.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping("/getCartListPage")
    public String getCartListPage(HttpSession session, Model model) {
        //User user = (User) session.getAttribute("user");
        List<CartVO> list = cartService.selectByUserId(21);
        model.addAttribute("list", list);
        return "cart_list";
    }

    @RequestMapping("/updateChecked")
    @ResponseBody
    public JSONResult updateChecked(Integer id, Integer checked) {
        cartService.updateChecked(id, checked);
        return JSONResult.ok("更新成功");
    }
}
