package com.cyk.springboot3.redis.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyk
 * @date 2023/10/28 09:09
 */
@SpringBootApplication
public class SentinelRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelRedisApplication.class,args);
    }

}
