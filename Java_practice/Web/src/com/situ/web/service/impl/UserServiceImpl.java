package com.situ.web.service.impl;

import com.situ.web.dao.IUserDao;
import com.situ.web.dao.impl.UserDaoImpl;
import com.situ.web.pojo.User;
import com.situ.web.service.IUserService;
import com.situ.web.util.JSONResult;
import com.situ.web.util.LayUITableJSONResult;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private IUserDao userDao = new UserDaoImpl();

    @Override
    public LayUITableJSONResult selectByPage(Integer page, Integer limit) {
        int offset = (page - 1) * limit;
        List<User> list = userDao.selectPage(offset, limit);
        Integer totalCount = userDao.selectTotalCount();

        return LayUITableJSONResult.ok(totalCount, list);
    }

    @Override
    public JSONResult deleteById(int id) {
        userDao.deleteById(id);
        return JSONResult.ok("删除成功");
    }

    @Override
    public JSONResult deleteAll(String[] ids) {
        // delete from user where id in(5,6);
        for (String id : ids) {
            userDao.deleteById(Integer.parseInt(id));
        }
        return JSONResult.ok("删除成功");
    }

    @Override
    public JSONResult add(User user) {
        userDao.add(user);
        return JSONResult.ok("添加成功");
    }

    @Override
    public User selectById(int id) {
        return userDao.selectById(id);
    }

    @Override
    public JSONResult update(User user) {
        userDao.update(user);
        return JSONResult.ok("更新成功");
    }
}
