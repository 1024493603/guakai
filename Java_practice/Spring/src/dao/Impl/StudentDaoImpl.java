package dao.Impl;

import dao.IStudentDao;

public class StudentDaoImpl implements IStudentDao {
    @Override
    public void selectAll() {
        System.out.println("StudentDaoImpl.selectAll");
    }
}