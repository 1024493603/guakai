package com.practice.mall.service;

import com.practice.mall.pojo.Product;

import java.util.List;

public interface IProductService {
    Product selectById(Integer id);

    List<Product> selectListByCategoryId(Integer id);
}
