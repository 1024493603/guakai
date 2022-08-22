package com.situ.web.service;

import com.situ.web.pojo.User;
import com.situ.web.util.JSONResult;
import com.situ.web.util.LayUITableJSONResult;

public interface IUserService {
    LayUITableJSONResult selectByPage(Integer page, Integer limit);

    JSONResult deleteById(int id);

    JSONResult deleteAll(String[] ids);

    JSONResult add(User user);

    User selectById(int id);

    JSONResult update(User user);
}
