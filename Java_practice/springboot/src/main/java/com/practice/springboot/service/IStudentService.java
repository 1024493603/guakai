package com.practice.springboot.service;

import com.practice.springboot.pojo.Student;

import java.util.List;

public interface IStudentService {
    List<Student> selectAll();
}
