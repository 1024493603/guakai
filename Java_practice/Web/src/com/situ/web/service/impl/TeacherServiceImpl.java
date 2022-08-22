package com.situ.web.service.impl;

import com.situ.web.dao.ITeacherDao;
import com.situ.web.dao.impl.TeacherDaoImpl;
import com.situ.web.pojo.Teacher;
import com.situ.web.service.ITeacherService;
import com.situ.web.util.PageInfo;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService {
    private ITeacherDao teacherDao = new TeacherDaoImpl();

    @Override
    public PageInfo selectByPage(Integer pageNo, Integer pageSize) {
        // 封装PageInfo  List<Student>
        PageInfo<Teacher> pageInfo = new PageInfo();
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);
        // 分页涉及两条sql语句
        // 1、查询当前页的数据
        int offset = (pageNo - 1) * pageSize;
        List<Teacher> list = teacherDao.selectPage(offset, pageSize);
        pageInfo.setList(list);
        // 2、查询总的数量
        int totalCount = teacherDao.selectTotalCount();
        // int totalPage = totalCount / pageSize;
        int totalPage = (int)Math.ceil((double)totalCount / pageSize);
        pageInfo.setTotalPage(totalPage);

        return pageInfo;
    }

    @Override
    public void deleteById(int id) {
        teacherDao.deleteById(id);
    }
}
