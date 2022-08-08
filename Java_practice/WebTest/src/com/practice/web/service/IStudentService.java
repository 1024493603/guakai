package com.practice.web.service;

import com.practice.web.pojo.Student;
import com.practice.web.util.StudentPageInfo;

public interface IStudentService {
    StudentPageInfo getStudentPageInfo(Integer pageNo, Integer pageSize);
    void deleteById(Integer id);
    void add(String name, Integer age, String gender);
    Student getStudentUpdateInfo(Integer id);
    void update(Student student);
}
