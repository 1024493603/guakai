package com.practice.web.service.impl;

import com.practice.web.dao.ITeacherDao;
import com.practice.web.dao.impl.TeacherDaoImpl;
import com.practice.web.pojo.Teacher;
import com.practice.web.service.ITeacherService;
import com.practice.web.util.TeacherPageInfo;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService {
    private ITeacherDao iTeacherDao = new TeacherDaoImpl();
    @Override
    public TeacherPageInfo selectByPage(Integer pageNo, Integer pageSize) {
        TeacherPageInfo teacherPageInfo = new TeacherPageInfo();
        teacherPageInfo.setPageNo(pageNo);
        teacherPageInfo.setPageSize(pageSize);

        int offset = (pageNo - 1) * pageSize;
        List<Teacher> teachers = iTeacherDao.selectPage(offset, pageSize);
        teacherPageInfo.setList(teachers);

        int totalPage = (int) Math.ceil((double)iTeacherDao.getCountPage() / pageSize);
        teacherPageInfo.setTotalPage(totalPage);

        return teacherPageInfo;
    }

    @Override
    public void deleteById(Integer id) {
        iTeacherDao.deleteById(id);
    }

    @Override
    public void add(String name, Integer age, String address) {
        iTeacherDao.add(name, age, address);
    }

    @Override
    public Teacher getUpdateTeacher(Integer id) {
        return iTeacherDao.getUpdateTeacher(id);
    }

    @Override
    public void update(Teacher teacher) {
        iTeacherDao.update(teacher);
    }
}
