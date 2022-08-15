package com.practice.springmvc.service;

import com.practice.springmvc.pojo.Student;
import com.practice.springmvc.util.PageInfo;

public interface IStudentService {
    PageInfo<Student> selectByPage(Integer pageNo, Integer pageSize);
    void deleteById(Integer id);
    void add(Student student);
    Student selectById(Integer id);
    void update(Student student);
}
