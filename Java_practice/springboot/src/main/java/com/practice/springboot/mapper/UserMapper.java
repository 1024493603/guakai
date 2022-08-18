package com.practice.springboot.mapper;

import com.practice.springboot.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User login(@Param("name") String name, @Param("password") String password);
}
