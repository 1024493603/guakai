package com.practice.springboot.service;

import com.practice.springboot.pojo.Student;
import com.practice.springboot.util.LayUITableJSONResult;

import java.util.List;

public interface IStudentService {
    List<Student> selectAll();
    LayUITableJSONResult selectByPage(Integer page, Integer limit);
}
