package com.practice.mall.service;

import com.practice.mall.pojo.Product;
import com.practice.mall.util.JSONResult;
import com.practice.mall.util.LayUITableJSONResult;

public interface IProductService {
    public Product selectById(Integer id);

    LayUITableJSONResult selectByPage(Integer page, Integer limit);

    JSONResult add(Product product);
}
