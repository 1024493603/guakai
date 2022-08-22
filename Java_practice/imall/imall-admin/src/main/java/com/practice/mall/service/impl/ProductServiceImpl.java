package com.practice.mall.service.impl;

import com.practice.mall.mapper.ProductMapper;
import com.practice.mall.pojo.Product;
import com.practice.mall.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product selectById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }
}
