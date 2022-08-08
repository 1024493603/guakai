package com.practice.web.service.impl;

import com.practice.web.dao.IStudentDao;
import com.practice.web.dao.impl.StudentDaoImpl;
import com.practice.web.pojo.Student;
import com.practice.web.service.IStudentService;
import com.practice.web.util.StudentPageInfo;

import java.util.List;

public class StudentServiceImpl implements IStudentService {
    IStudentDao iStudentDao = new StudentDaoImpl();
    @Override
    public StudentPageInfo getStudentPageInfo(Integer pageNo, Integer pageSize) {
        StudentPageInfo studentPageInfo = new StudentPageInfo();
        studentPageInfo.setPageNo(pageNo);
        studentPageInfo.setPageSize(pageSize);

        int offset = (pageNo - 1) * pageSize;
        List<Student> students = iStudentDao.selectPage(offset, pageSize);
        studentPageInfo.setList(students);

        int page = iStudentDao.getCountPage();
        int totalPage = (int) Math.ceil((double)page / pageSize);
        studentPageInfo.setTotalPage(totalPage);
        return studentPageInfo;
    }

    @Override
    public void deleteById(Integer id) {
        iStudentDao.deleteById(id);
    }

    @Override
    public void add(String name, Integer age, String gender) {
        iStudentDao.add(name, age, gender);
    }

    @Override
    public Student getStudentUpdateInfo(Integer id) {
        return iStudentDao.getStudentUpdateInfo(id);
    }

    @Override
    public void update(Student student) {
        iStudentDao.update(student);
    }
}
