package com.practice.spring.service.Impl;

import com.practice.spring.dao.IBanjiDao;
import com.practice.spring.service.IBanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BanjiServiceImpl implements IBanjiService {
    @Autowired
    private IBanjiDao banjiDao;

    @Override
    public void selectAll() {
        System.out.println("BanjiServiceImpl.selectAll");
        banjiDao.selectAll();
    }
}
