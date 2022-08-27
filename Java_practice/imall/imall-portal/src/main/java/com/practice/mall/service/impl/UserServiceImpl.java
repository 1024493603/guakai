package com.practice.mall.service.impl;

import com.practice.mall.mapper.UserMapper;
import com.practice.mall.pojo.User;
import com.practice.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }
}
