package com.practice.mall.service;

import com.practice.mall.pojo.Cart;
import com.practice.mall.pojo.vo.CartVO;

import java.util.List;

public interface ICartService {
    void add(Cart cart);

    List<CartVO> selectByUserId(Integer id);
}
