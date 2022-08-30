package com.practice.mall.service;

import com.practice.mall.pojo.Order;
import com.practice.mall.pojo.vo.OrderVO;

import java.util.List;

public interface IOrderService {
    void add(Order order);

    List<OrderVO> selectByUserId(Integer id);
}
