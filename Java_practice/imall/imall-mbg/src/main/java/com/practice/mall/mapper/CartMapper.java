package com.practice.mall.mapper;

import com.practice.mall.pojo.Cart;
import com.practice.mall.pojo.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<CartVO> selectByUserId(Integer id);

    void updateChecked(@Param("id") Integer id, @Param("checked") Integer checked);

    int selectByUserIdAndProductId(Cart cart);

    void updateQuantityByUserIdAndProductId(Cart cart);
}