package com.practice.springboot.service.impl;

import com.practice.springboot.mapper.StudentMapper;
import com.practice.springboot.pojo.Student;
import com.practice.springboot.service.IStudentService;
import com.practice.springboot.util.LayUITableJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    //此处报错不管
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }

    @Override
    public LayUITableJSONResult selectByPage(Integer page, Integer limit) {
        int offset = (page - 1) * limit;
        List<Student> list = studentMapper.selectByPage(offset, limit);
        int totalCount = studentMapper.selectTotalCount();

        return LayUITableJSONResult.ok(totalCount, list);
    }
}
