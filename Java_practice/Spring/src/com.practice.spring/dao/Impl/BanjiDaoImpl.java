package com.practice.spring.dao.Impl;

import com.practice.spring.dao.IBanjiDao;
import org.springframework.stereotype.Repository;

@Repository
public class BanjiDaoImpl implements IBanjiDao {
    @Override
    public void selectAll() {
        System.out.println("BnajiDaoImpl.selectAll");
    }
}
