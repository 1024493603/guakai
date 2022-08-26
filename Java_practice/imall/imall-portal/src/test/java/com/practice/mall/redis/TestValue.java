package com.practice.mall.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class TestValue {

    /*@Autowired
    ProductMapper productMapper;*/

    @Autowired
    private RedisTemplate redisTemplate;

    // operation
    @Test
    public void setValue() {
        redisTemplate.opsForValue().set("name", "Android11");
    }

    @Test
    public void getValue() {
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    public void deleteValue() {
        redisTemplate.delete("name");
    }

}
