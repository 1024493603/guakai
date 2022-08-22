package com.situ.web.service.impl;

import com.situ.web.dao.IStudentDao;
import com.situ.web.dao.impl.StudentDaoImpl;
import com.situ.web.pojo.Student;
import com.situ.web.pojo.vo.StudentBanjiVO;
import com.situ.web.service.IStudentService;

import java.util.List;

public class StudentServiceImpl implements IStudentService {
    private IStudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<StudentBanjiVO> selectAll() {
        return studentDao.selectAll();
    }

    @Override
    public void deleteById(Integer id) {
        studentDao.deleteById(id);
    }

    @Override
    public void add(Student student) {
        studentDao.add(student);
    }

    @Override
    public Student selectById(Integer id) {
        return studentDao.selectById(id);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }
}
