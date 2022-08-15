package com.practice.ssm.mapper;

import com.practice.ssm.pojo.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> selectAll();
}
