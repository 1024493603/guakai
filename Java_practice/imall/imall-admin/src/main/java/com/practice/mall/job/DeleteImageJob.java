package com.practice.mall.job;

import com.practice.mall.constant.RedisConstant;
import com.practice.mall.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@Configuration
public class DeleteImageJob {

    @Autowired
    private RedisTemplate redisTemplate;
                        //秒 分 时 日 月 星期
    @Scheduled(cron = "0 0/1 * * * ?")
    public void deleteImage() {
        System.out.println("DeleteImageJob.deleteImage");
        // 根据Redis里面保存的两个Set集合进行差值运算，获得没有使用的图片
        Set<String> difference = redisTemplate.opsForSet().difference(RedisConstant.UPLOAD_IMAGE, RedisConstant.UPLOAD_IMAGE_TO_DB);
        if (difference != null){
            for (String imageName : difference) {
                //删除七牛云上的图片
                QiniuUtils.deleteFileFromQiniu(imageName);
                //从Redis集合中删除多余的图片
                redisTemplate.opsForSet().remove(RedisConstant.UPLOAD_IMAGE, imageName);
                System.out.println("已删除文件" + imageName);
            }
        }
    }
}
