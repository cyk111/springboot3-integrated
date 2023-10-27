package com.cyk.springboot3.redis.lettuce.config;

import com.cyk.springboot3.redis.lettuce.annotation.RedisLock;
import com.cyk.springboot3.redis.lettuce.utils.RedisDistributedLock;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author cyk
 * @date 2023/10/27 16:20
 */
@Slf4j
@Aspect
@Configuration
public class RedisLockAspect {


    /**
     * lock impl.
     */
    @Resource
    private RedisDistributedLock distributedLock;

    /**
     * AOP, around PJP.
     *
     * @param pjp ProceedingJoinPoint
     * @return Object
     * @throws Throwable Throwable
     */
    @Around("@annotation(com.cyk.springboot3.redis.lettuce.annotation.RedisLock)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // get attribute through annotation
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String key = redisLock.value();
        if (StringUtils.isEmpty(key)) {
            Object[] args = pjp.getArgs();
            key = Arrays.toString(args);
        }

        // do lock
        boolean lock = distributedLock.lock(key, redisLock.expireMills(), redisLock.retryTimes(),
                redisLock.retryDurationMills());
        if (!lock) {
            log.debug("get lock failed, key: {}", key);
            return null;
        }

        // execute method, and unlock
        log.debug("get lock success, key: {}", key);
        try {
            // execute
            return pjp.proceed();
        } catch (Exception e) {
            log.error("execute locked method occurred an exception", e);
        } finally {
            // unlock
            boolean releaseResult = distributedLock.unlock(key);
            log.debug("release lock: {}, success: {}", key, releaseResult);
        }

        return null;
    }
}
