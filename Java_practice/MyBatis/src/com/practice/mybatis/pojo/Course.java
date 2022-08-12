package com.practice.mybatis.pojo;

import java.util.List;

public class Course {
    private Integer id;
    private String name;
    private Integer credit;
    //一个课程可以被多个班级学习
    private List<Banji> banjiList;
}
