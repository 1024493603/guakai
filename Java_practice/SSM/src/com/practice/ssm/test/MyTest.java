package com.practice.ssm.test;

import com.practice.ssm.mapper.StudentMapper;
import com.practice.ssm.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//下两条为在SSM中使用单元测试

//帮助我们创建IOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//加载Spring的核心配置文件
@ContextConfiguration("classpath:applicationContext.xml")
public class MyTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void test1() {
        List<Student> list = studentMapper.selectAll();
        for (Student student : list) {
            System.out.println(student);
        }
    }
}
