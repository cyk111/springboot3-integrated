package com.cyk.springboot3.redis.cluster.controller;

import com.cyk.springboot3.redis.cluster.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/11/3 10:05
 */
@RestController
public class TestController {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/test")
    public void test(){

        stringRedisTemplate.opsForValue().set("ke1","abc");
        User u = new User();
        u.setName("cyk");
        u.setAddress("abc.df.cd");
        u.setAge(13);
        redisTemplate.opsForValue().set("user1",u);

    }
}
