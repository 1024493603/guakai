package com.situ.web.service;

import com.situ.web.util.PageInfo;

public interface ITeacherService {
    PageInfo selectByPage(Integer pageNo, Integer pageSize);

    void deleteById(int id);
}
