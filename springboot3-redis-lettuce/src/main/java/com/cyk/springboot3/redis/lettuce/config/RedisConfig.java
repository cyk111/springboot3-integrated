package com.cyk.springboot3.redis.lettuce.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * @author cyk
 * @date 2023/10/27 15:03
 */
@Configuration
public class RedisConfig {
    /**
     * RedisTemplate模板
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

     /*
        JDK 序列化方式 （默认）
        String 序列化方式 (较长使用)
        JSON 序列化方式 (占用空间太大)
        XML 序列化方式 (很少使用)

        Spring Boot 集成 Redis 时，默认使用 JdkSerializationRedisSerializer 进行对象序列化和反序列化。但是，
        这种序列化方式有一些问题，例如效率低下，占用内存高等。因此，通常情况下建议使用其他序列化方式。
        Spring Boot 提供了多种序列化方式，例如 JSON、Jackson、FastJson、Protobuf、Kryo 等。其中，
        使用 JSON 序列化的方式是比较常见的选择，因为 JSON 格式的数据在传输和存储上都比较便捷。
        */
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //创建一个json的序列化对象
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        //设置value的序列化方式json
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        //设置key序列化方式String
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置hash key序列化方式String
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //设置hash value序列化json
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        // 设置支持事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisSerializer<Object> redisSerializer() {
        //创建JSON序列化器
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //必须设置，否则无法将JSON转化为对象，会转化成Map类型
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    /**
     * StringRedisTemplate模板
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        return stringRedisTemplate;
    }
    /**
     * Redis分布式锁
     */
    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory factory){

        // 1、锁的密钥前缀：REDIS-LOCK
        // 2、锁的过期时间：20秒
        return new RedisLockRegistry(factory, "REDIS-LOCK",20000L);
    }
}
