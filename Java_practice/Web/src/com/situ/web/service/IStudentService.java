package com.situ.web.service;

import com.situ.web.pojo.Student;
import com.situ.web.pojo.vo.StudentBanjiVO;

import java.util.List;

public interface IStudentService {
    List<StudentBanjiVO> selectAll();
    void deleteById(Integer id);
    void add(Student student);
    Student selectById(Integer id);
    void update(Student student);

}
