package com.practice.web.service;

import com.practice.web.pojo.User;
import com.practice.web.util.JSONResult;
import com.practice.web.util.LayUITableJSONResult;

public interface IUserService {
    LayUITableJSONResult selectByPage(Integer page, Integer limit);
    JSONResult deleteById(int id);

    JSONResult deleteAll(String[] ids);

    JSONResult add(User user);

    User selectById(int id);

    JSONResult update(User user);
}
