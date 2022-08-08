package com.practice.web.util;

import com.practice.web.pojo.Teacher;

import java.util.List;

public class TeacherPageInfo {
    private List<Teacher> list;
    //一共多少页
    private Integer totalPage;
    //当前多少页
    private Integer pageNo;
    //每页多少条数据
    private Integer pageSize;

    public TeacherPageInfo() {
    }

    public TeacherPageInfo(List<Teacher> list, Integer totalPage, Integer pageNo, Integer pageSize) {
        this.list = list;
        this.totalPage = totalPage;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "list=" + list +
                ", totalPage=" + totalPage +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }

    public List<Teacher> getList() {
        return list;
    }

    public void setList(List<Teacher> list) {
        this.list = list;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
