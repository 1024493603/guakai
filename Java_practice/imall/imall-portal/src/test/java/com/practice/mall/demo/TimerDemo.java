package com.practice.mall.demo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {

    public static void main(String[] args) {
        //Timer定时器
        //TimerTask:任务
        //firstTime:第一次执行的时间
        //peroid:间隔多久再执行
        Date date = new Date();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer task...");
            }
        }, date, 1000*2);
    }
}
