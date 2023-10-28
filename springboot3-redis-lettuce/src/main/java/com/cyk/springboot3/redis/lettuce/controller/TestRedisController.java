package com.cyk.springboot3.redis.lettuce.controller;

import com.cyk.springboot3.redis.lettuce.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/10/27 09:20
 */
@RestController
@RequestMapping("/redis")
public class TestRedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/test")
    public void test(){
        stringRedisTemplate.opsForValue().set("aaa","1111");
        String s = stringRedisTemplate.opsForValue().get("aaa");
        System.out.println("s======="+s);
    }

    // 计数器
    @RequestMapping("/counter")
    public Long counter(){
        Long v = redisUtil.incr("counter",1);
        System.out.println("v========="+v);
        return v;
    }



}
