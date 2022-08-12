package com.practice.ssm.dao;

import com.practice.ssm.pojo.Student;

import java.util.List;

public interface IStudentDao {
    List<Student> selectByPage(Integer offset, Integer pageSize);
    Integer countTotalNum();
}
