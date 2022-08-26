package com.practice.mall.job;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class DeleteImageJob {
                        //秒 分 时 日 月 星期
    @Scheduled(cron = "0/1 * * * * ?")
    public void deleteImage() {
        System.out.println("DeleteImageJob.deleteImage");
    }
}
