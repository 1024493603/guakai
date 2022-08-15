package com.practice.spring.dao.Impl;

import com.practice.spring.dao.IStudentDao;

public class StudentDaoImpl implements IStudentDao {
    @Override
    public void selectAll() {
        System.out.println("StudentDaoImpl.selectAll");
    }
}