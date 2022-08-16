package com.practice.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 声明该类是一个SpringBoot的引导类
@SpringBootApplication
// @MapperScan注解，扫描MyBatis Mapper接口类
@MapperScan("com.practice.springboot.mapper")
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
