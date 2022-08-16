package com.practice.maven.service;

import com.practice.maven.pojo.Student;

import java.util.List;

public interface IStudentService {
    List<Student> selectAll();
}
