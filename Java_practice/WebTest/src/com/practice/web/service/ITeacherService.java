package com.practice.web.service;

import com.practice.web.pojo.Teacher;
import com.practice.web.util.TeacherPageInfo;

public interface ITeacherService {
    TeacherPageInfo selectByPage(Integer pageNo, Integer pageSize);
    void deleteById(Integer id);
    void add(String name, Integer age, String address);
    Teacher getUpdateTeacher(Integer id);
    void update(Teacher teacher);
}
