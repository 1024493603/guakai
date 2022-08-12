package controller;

import service.IStudentService;

public class StudentController {
    // private IStudentService studentService = new StudentServiceImpl();
    private IStudentService studentService;

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    public void selectAll() {
        System.out.println("StudentController.selectAll");
        studentService.selectAll();
    }
}
