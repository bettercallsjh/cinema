package com.pmsj.cinema.common.util;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/6 17:03
 **/

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Timer {


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 1000)
//    public void testTasks3() {
//        System.out.println("定时任务执行时间11111：" + dateFormat.format(new Date()));
//    }
//
//
//    //每隔1秒执行一次
//    @Scheduled(fixedRate = 1000)
//    public void testTasks() {
//        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
//    }



    //每天3：05执行
    @Scheduled(cron = "0 00 00 ? * *")
    public void testTasks2() {
        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
    }

}
