package service.Impl;

import dao.IStudentDao;
import service.IStudentService;

public class StudentServiceImpl implements IStudentService {
    private IStudentDao studentDao;

    public void setStudentDao(IStudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void selectAll() {
        System.out.println("StudentServiceImpl.selectAll");
        studentDao.selectAll();
    }
}
