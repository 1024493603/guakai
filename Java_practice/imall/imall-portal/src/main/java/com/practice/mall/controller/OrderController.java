package com.practice.mall.controller;

import com.practice.mall.pojo.Order;
import com.practice.mall.pojo.Shipping;
import com.practice.mall.pojo.User;
import com.practice.mall.pojo.vo.CartVO;
import com.practice.mall.service.ICartService;
import com.practice.mall.service.IOrderService;
import com.practice.mall.service.IShippingService;
import com.practice.mall.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IShippingService shippingService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/getConfirmOrderPage")
    public String getConfirmOrderPage(HttpSession session, Model model) {
       // User user = (User) session.getAttribute("user");
        List<Shipping> shippingList = shippingService.selectByUserId(21);
        model.addAttribute("shippingList", shippingList);

        List<CartVO> cartVOList = cartService.selectByUserIdAndChecked(21);
        model.addAttribute("cartVOList", cartVOList);
        return "confirm_order";
    }

    @RequestMapping("/add")
    @ResponseBody
    public JSONResult add(Order order, HttpSession session) {
        //User user = (User) session.getAttribute("user");
        order.setUserId(21);
        orderService.add(order);
        return JSONResult.ok("生成订单成功");
    }
}
