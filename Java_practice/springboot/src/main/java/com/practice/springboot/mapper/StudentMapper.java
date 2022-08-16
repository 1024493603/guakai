package com.practice.springboot.mapper;

import com.practice.springboot.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    List<Student> selectAll();
    List<Student> selectByPage(@Param("offset") int offset, @Param("limit") Integer limit);
    int selectTotalCount();
}
