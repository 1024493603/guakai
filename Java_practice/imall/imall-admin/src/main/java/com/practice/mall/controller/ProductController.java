package com.practice.mall.controller;

import com.practice.mall.pojo.Product;
import com.practice.mall.service.IProductService;
import com.practice.mall.util.JSONResult;
import com.practice.mall.util.LayUITableJSONResult;
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

    @RequestMapping("/getProductListPage")
    public String getProductListPage() {
        return "product_list";
    }

    @RequestMapping("/selectByPage")
    @ResponseBody
    public LayUITableJSONResult selectByPage(Integer page, Integer limit) {
        return productService.selectByPage(page, limit);
    }

    @RequestMapping("/getProductAddPage")
    public String getProductAddPage() {
        return "product_add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public JSONResult add(Product product) {
        return productService.add(product);
    }
}
