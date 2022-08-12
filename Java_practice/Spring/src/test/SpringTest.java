package test;

import controller.StudentController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Student;

public class SpringTest {
    @Test
    public void test1() {
        // 创建Spring容器，在xml中配置的bean都会new出对象放到Spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 从Spring容器中根据名字取出指定的new出来的对象
        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }

    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentController studentController = (StudentController) context.getBean("studentController");
        studentController.selectAll();
    }
}
