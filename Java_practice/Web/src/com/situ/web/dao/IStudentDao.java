package com.situ.web.dao;

import com.situ.web.pojo.Student;
import com.situ.web.pojo.vo.StudentBanjiVO;

import java.util.List;

public interface IStudentDao {
    List<StudentBanjiVO> selectAll();
    void deleteById(Integer id);
    void add(Student student);
    Student selectById(Integer id);
    void update(Student student);
}
