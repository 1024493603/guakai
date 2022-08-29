package com.practice.mall.service;

import com.practice.mall.pojo.Shipping;

import java.util.List;

public interface IShippingService {
    List<Shipping> selectByUserId(Integer id);
}
