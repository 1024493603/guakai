package com.practice.mall.controller;

import com.practice.mall.pojo.Category;
import com.practice.mall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    //private Map<String, List<Category>> map = new HashMap<>();
    // 设置项目默认的首页（欢迎页）
    // http://localhost:8080/
    // 如果是80端口号，可以不用写
    // http://localhost/
    @RequestMapping("/")
    public String index(Model model) {
        System.out.println("IndexController.index");
        //用Map实现Redis
        //缺陷
        // 1.关闭服务器之后清空
        // 2.无法实现分布式
        /*List<Category> topCategoryList = map.get("topCategoryList");
        if (CollectionUtils.isEmpty(topCategoryList)) {
            topCategoryList = categoryService.selectTopCategoryList();      //如果为空则查询数据库放入Map
            map.put("topCategoryList", topCategoryList);
        }
        List<Category> secondCategoryList = map.get("secondCategoryList");  //二级分类同样
        if (CollectionUtils.isEmpty(secondCategoryList)) {
            secondCategoryList = categoryService.selectSecondCategoryList();
            map.put("secondCategoryList", secondCategoryList);
        }*/

        // 1.首先从Redis取数据
        List<Category> topCategoryList = redisTemplate.opsForList().range("topCategoryList", 0, -1);
        // 2、Redis中没有
        if (CollectionUtils.isEmpty(topCategoryList)) {
            System.out.println("Redis中没有topCategoryList，从数据库中取");
            topCategoryList = categoryService.selectTopCategoryList();
            //3、数据库中取出来后，更新到Redis中
            redisTemplate.opsForList().rightPushAll("topCategoryList", topCategoryList);
        }
        List<Category> secondCategoryList = redisTemplate.opsForList().range("secondCategoryList", 0, -1);
        if (CollectionUtils.isEmpty(secondCategoryList)) {
            System.out.println("Redis中没有secondCategoryList，从数据库中取");
            secondCategoryList = categoryService.selectSecondCategoryList();
            redisTemplate.opsForList().rightPushAll("secondCategoryList", secondCategoryList);
        }

        model.addAttribute("topCategoryList", topCategoryList);
        model.addAttribute("secondCategoryList", secondCategoryList);
        return "index";
    }
}
