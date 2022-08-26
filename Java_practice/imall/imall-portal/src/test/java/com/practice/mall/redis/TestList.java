package com.practice.mall.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
public class TestList {

    @Autowired
    private RedisTemplate redisTemplate;

    // 右压栈
    @Test
    public void setValue() {
        redisTemplate.opsForList().rightPush("nameList", "张飞");
        redisTemplate.opsForList().rightPush("nameList", "关羽");
        redisTemplate.opsForList().rightPush("nameList", "赵云");

//        redisTemplate.opsForList().rightPushAll()
    }

    @Test
    public void getValue() {
        List list = redisTemplate.opsForList().range("nameList", 0, -1);
        System.out.println(list);
    }

    @Test
    public void testSearchById() {
        String name = (String) redisTemplate.opsForList().index("nameList", 1);
        System.out.println(name);
    }


    @Test
    public void deleteValue() {
        redisTemplate.delete("nameList");
    }

}
