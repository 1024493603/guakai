package com.practice.web.dao;

import com.practice.web.pojo.Teacher;

import java.util.List;

public interface ITeacherDao {
    int getCountPage();
    List<Teacher> selectPage(Integer offset, Integer pageSize);
    void deleteById(Integer id);
    void add(String name, Integer age, String address);
    Teacher getUpdateTeacher(Integer id);
    void update(Teacher teacher);
}
