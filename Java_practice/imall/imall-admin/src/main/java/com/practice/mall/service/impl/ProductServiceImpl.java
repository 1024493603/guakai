package com.practice.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.mall.mapper.ProductMapper;
import com.practice.mall.pojo.Product;
import com.practice.mall.service.IProductService;
import com.practice.mall.util.LayUITableJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product selectById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public LayUITableJSONResult selectByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Product> list = productMapper.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        long totalCount = pageInfo.getTotal();

        return LayUITableJSONResult.ok((int)totalCount, list);
    }
}
