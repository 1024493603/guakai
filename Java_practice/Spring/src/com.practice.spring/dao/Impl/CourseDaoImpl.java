package com.practice.spring.dao.Impl;

import com.practice.spring.dao.ICourseDao;
import org.springframework.stereotype.Repository;

/*<bean name="studentDao" class="com.situ.spring.dao.impl.StudentDaoImpl">
</bean>*/
@Repository("courseDao")
public class CourseDaoImpl implements ICourseDao {

    @Override
    public void selectAll() {
        System.out.println("CourseDaoImpl.selectAll");
    }
}