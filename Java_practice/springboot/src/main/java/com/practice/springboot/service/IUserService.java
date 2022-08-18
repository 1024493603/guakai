package com.practice.springboot.service;

import com.practice.springboot.pojo.User;

public interface IUserService {
    User login(String name, String password);
}
