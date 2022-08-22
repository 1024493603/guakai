package com.situ.web.dao;

import com.situ.web.pojo.User;

import java.util.List;

public interface IUserDao {
    List<User> selectPage(Integer offset, Integer limit);
    Integer selectTotalCount();

    void deleteById(int id);

    void add(User user);

    User selectById(int id);

    void update(User user);
}
