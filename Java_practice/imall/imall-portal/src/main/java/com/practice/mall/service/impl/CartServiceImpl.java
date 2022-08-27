package com.practice.mall.service.impl;

import com.practice.mall.mapper.CartMapper;
import com.practice.mall.pojo.Cart;
import com.practice.mall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public void add(Cart cart) {
        cartMapper.insert(cart);
    }
}
