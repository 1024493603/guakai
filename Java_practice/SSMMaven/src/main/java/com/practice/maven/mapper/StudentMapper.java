package com.practice.maven.mapper;

import com.practice.maven.pojo.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> selectAll();
}
