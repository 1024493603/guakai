package com.practice.springboot.mapper;

import com.practice.springboot.pojo.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> selectAll();
}
