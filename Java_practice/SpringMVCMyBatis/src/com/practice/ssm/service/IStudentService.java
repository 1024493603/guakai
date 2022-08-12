package com.practice.ssm.service;

import com.practice.ssm.pojo.Student;
import com.practice.ssm.util.PageInfo;

public interface IStudentService {
    PageInfo<Student> selectByPage(Integer pageNo, Integer pageSize);
}
