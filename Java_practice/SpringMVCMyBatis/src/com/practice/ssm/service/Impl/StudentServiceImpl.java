package com.practice.ssm.service.Impl;

import com.practice.ssm.dao.IStudentDao;
import com.practice.ssm.dao.Impl.StudentDaoImpl;
import com.practice.ssm.pojo.Student;
import com.practice.ssm.service.IStudentService;
import com.practice.ssm.util.PageInfo;

import java.util.List;

public class StudentServiceImpl implements IStudentService {
    private IStudentDao iStudentDao = new StudentDaoImpl();

    @Override
    public PageInfo<Student> selectByPage(Integer pageNo, Integer pageSize) {
        Integer offset = (pageNo - 1) * pageSize;
        List<Student> list = iStudentDao.selectByPage(offset, pageSize);

        Integer page = iStudentDao.countTotalNum();
        Integer totalPage = (int) Math.ceil((double)page / pageSize);

        return new PageInfo<>(list, totalPage, pageNo, pageSize);
    }
}
