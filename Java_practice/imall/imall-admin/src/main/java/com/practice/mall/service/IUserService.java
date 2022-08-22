package com.practice.mall.service;

import com.practice.mall.pojo.User;

public interface IUserService {
    User login(String name, String password);
}
