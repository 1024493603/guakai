package com.practice.spring.service.Impl;

import com.practice.spring.dao.ICourseDao;
import com.practice.spring.service.ICourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*<bean name="studentService" class="com.situ.spring.service.impl.StudentServiceImpl">
    <property name="studentDao" ref="studentDao"/>
</bean>*/
@Service("courseService")
public class CourseServiceImpl implements ICourseService {
    //  <property name="studentDao" ref="studentDao"/>
    // @Resource：从Spring容器中根据名字拿出指定的对象注入进来
    @Resource(name = "courseDao")
    private ICourseDao courseDao;

    @Override
    public void selectAll() {
        System.out.println("CourseServiceImpl.selectAll");
        courseDao.selectAll();
    }
}