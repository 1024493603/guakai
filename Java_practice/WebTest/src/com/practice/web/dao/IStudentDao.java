package com.practice.web.dao;

import com.practice.web.pojo.Student;

import java.util.List;

public interface IStudentDao {
    void deleteById(Integer id);
    void add(String name, Integer age, String gender);
    Student getStudentUpdateInfo(Integer id);
    void update(Student student);
    int getCountPage();
    List<Student> selectPage(Integer offset,Integer pageSize);
}
