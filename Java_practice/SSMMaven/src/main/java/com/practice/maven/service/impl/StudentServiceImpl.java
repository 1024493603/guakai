package com.practice.maven.service.impl;

import com.practice.maven.mapper.StudentMapper;
import com.practice.maven.pojo.Student;
import com.practice.maven.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }
}
