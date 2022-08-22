package com.situ.web.dao;

import com.situ.web.pojo.Teacher;

import java.util.List;

public interface ITeacherDao {
    List<Teacher> selectPage(int offset, Integer pageSize);

    int selectTotalCount();

    void deleteById(int id);
}
