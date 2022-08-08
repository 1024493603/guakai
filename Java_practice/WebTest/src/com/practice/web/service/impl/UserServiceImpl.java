package com.practice.web.service.impl;

import com.practice.web.dao.IUserDao;
import com.practice.web.dao.impl.IUserDaoImpl;
import com.practice.web.pojo.User;
import com.practice.web.service.IUserService;
import com.practice.web.util.JSONResult;
import com.practice.web.util.LayUITableJSONResult;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private IUserDao iUserDao = new IUserDaoImpl();

    @Override
    public LayUITableJSONResult selectByPage(Integer page, Integer limit) {
        System.out.println("UserServiceImpl.selectByPage");
        int offset = (page - 1) * limit;
        List<User> list = iUserDao.selectPage(offset, limit);
        Integer totalCount = iUserDao.selectTotalCount();

        return LayUITableJSONResult.ok(totalCount, list);
    }

    @Override
    public JSONResult deleteById(int id) {
        iUserDao.deleteById(id);
        return JSONResult.ok("删除成功");
    }

    @Override
    public JSONResult deleteAll(String[] ids) {
        for (String id : ids) {
            iUserDao.deleteById(Integer.parseInt(id));
        }

        return JSONResult.ok("删除成功");
    }

    @Override
    public JSONResult add(User user) {
        iUserDao.add(user);
        return JSONResult.ok("添加成功");
    }

    @Override
    public User selectById(int id) {
        return iUserDao.selectById(id);
    }

    @Override
    public JSONResult update(User user) {
        iUserDao.update(user);
        return JSONResult.ok("修改成功");
    }
}
