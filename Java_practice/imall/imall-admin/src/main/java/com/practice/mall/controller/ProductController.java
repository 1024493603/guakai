package com.practice.mall.controller;

import com.practice.mall.pojo.Product;
import com.practice.mall.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;


    @RequestMapping("/selectById")
    @ResponseBody
    public Product selectById(Integer id) {
        return productService.selectById(id);
    }
}
