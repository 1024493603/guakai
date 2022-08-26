package com.practice.mall.service;

import com.practice.mall.pojo.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> selectTopCategoryList();

    List<Category> selectSecondCategoryList();
}
