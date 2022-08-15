package com.practice.springmvc.dao;

import com.practice.springmvc.pojo.Student;

import java.util.List;

public interface IStudentDao {
    List<Student> selectByPage(Integer offset, Integer pageSize);
    Integer countTotalNum();
    void deleteById(Integer id);
    void add(Student student);
    Student selectById(Integer id);
    void update(Student student);
}
