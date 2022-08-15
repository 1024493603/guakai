package com.practice.ssm.service.impl;

import com.practice.ssm.mapper.StudentMapper;
import com.practice.ssm.pojo.Student;
import com.practice.ssm.service.IStudentService;
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
