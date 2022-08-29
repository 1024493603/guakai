package com.practice.mall.service.impl;

import com.practice.mall.mapper.CartMapper;
import com.practice.mall.pojo.Cart;
import com.practice.mall.pojo.vo.CartVO;
import com.practice.mall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public void add(Cart cart) {
        //1、根据user_id和product_id来cart表看这个用户是否有这个商品
        //2、如果有了则更新数量
        //3、如果没有则执行insert
        int count = cartMapper.selectByUserIdAndProductId(cart);
        if (count >= 1) {
            cartMapper.updateQuantityByUserIdAndProductId(cart);
        } else {
            cartMapper.insert(cart);
        }
    }

    @Override
    public List<CartVO> selectByUserId(Integer id) {
        return cartMapper.selectByUserId(id);
    }

    @Override
    public void updateChecked(Integer id, Integer checked) {
        cartMapper.updateChecked(id, checked);
    }
}
