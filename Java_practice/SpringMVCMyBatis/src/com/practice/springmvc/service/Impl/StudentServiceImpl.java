package com.practice.springmvc.service.Impl;

import com.practice.springmvc.dao.IStudentDao;
import com.practice.springmvc.dao.Impl.StudentDaoImpl;
import com.practice.springmvc.pojo.Student;
import com.practice.springmvc.service.IStudentService;
import com.practice.springmvc.util.PageInfo;

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

    @Override
    public void deleteById(Integer id) {
        iStudentDao.deleteById(id);
    }

    @Override
    public void add(Student student) {
        iStudentDao.add(student);
    }

    @Override
    public Student selectById(Integer id) {
        return iStudentDao.selectById(id);
    }

    @Override
    public void update(Student student) {
        iStudentDao.update(student);
    }


}
