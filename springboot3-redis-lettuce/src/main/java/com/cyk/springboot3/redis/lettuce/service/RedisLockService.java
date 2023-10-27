package com.cyk.springboot3.redis.lettuce.service;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author cyk
 * @date 2023/10/27 15:13
 */
@Service
public class RedisLockService {


    @Resource
    private RedisTemplate<String,Object> redisTemplate ;

    public Boolean lock (String lock,long timeout){
        return redisTemplate.opsForValue().setIfAbsent(lock,lock,timeout, TimeUnit.SECONDS);
    }
    public void unLock (String lock){
        Object lockVal = redisTemplate.opsForValue().get(lock);
        if (!Objects.isNull(lockVal) && Objects.equals(lockVal.toString(),lock)){
            redisTemplate.delete(lock) ;
        }
    }
}
