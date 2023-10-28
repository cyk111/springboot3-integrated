package com.cyk.springboot3.redis.lettuce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author cyk
 * @date 2023/10/28 21:18
 */
@Component
public class SchedulerTask {

    @Autowired
    private RedisTemplate redisTemplate;

    private String redisKey="qpsList";

    @Scheduled(cron="*/1 * * * * ?")
    private void process(){
        //一次性消费两个
        System.out.println("正在消费。。。。。。");
        redisTemplate.opsForList().trim(redisKey, 2, -1);
    }
}
