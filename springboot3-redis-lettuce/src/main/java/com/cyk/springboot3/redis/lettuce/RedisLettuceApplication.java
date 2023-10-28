package com.cyk.springboot3.redis.lettuce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author cyk
 * @date 2023/10/27 14:29
 */
@SpringBootApplication
@MapperScan(basePackages = "com.cyk.springboot3.redis.lettuce.mapper")
// 开启异步任务支持
@EnableAsync
public class RedisLettuceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisLettuceApplication.class,args);
    }

}
