package com.practice.mall.mapper;

import com.practice.mall.pojo.Order;
import com.practice.mall.pojo.vo.OrderVO;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderNo);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderVO> selectByUserId(Integer id);
}