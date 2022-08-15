package com.practice.ssm.service;

import com.practice.ssm.pojo.Student;

import java.util.List;

public interface IStudentService {
    List<Student> selectAll();
}
