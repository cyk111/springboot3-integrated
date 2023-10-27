package com.cyk.springboot3.redis.lettuce.controller;

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

    @RequestMapping("/test")
    public void test(){
        stringRedisTemplate.opsForValue().set("aaa","1111");
        String s = stringRedisTemplate.opsForValue().get("aaa");
        System.out.println("s======="+s);
    }

}
