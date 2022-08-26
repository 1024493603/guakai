package com.practice.mall.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

@SpringBootTest
public class TestSet {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void setValue() {
        redisTemplate.opsForSet().add("nameSet", "悟空");
        redisTemplate.opsForSet().add("nameSet", "沙僧");
        redisTemplate.opsForSet().add("nameSet", "八戒");
        redisTemplate.opsForSet().add("nameSet", "八戒");
    }

    @Test
    public void getValue() {
        Set nameSet = redisTemplate.opsForSet().members("nameSet");
        System.out.println(nameSet);
    }

    @Test
    public void deleteValue() {
        redisTemplate.opsForSet().remove("nameSet", "沙僧");
    }
}
