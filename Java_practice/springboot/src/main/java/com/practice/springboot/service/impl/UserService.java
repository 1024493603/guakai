package com.practice.springboot.service.impl;

import com.practice.springboot.mapper.UserMapper;
import com.practice.springboot.pojo.User;
import com.practice.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String name, String password) {
        return userMapper.login(name, password);
    }
}
