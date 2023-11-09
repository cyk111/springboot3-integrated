package com.cyk.springboot3.redis.sentinel.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author cyk
 * @date 2023/11/8 21:18
 */
@Configuration
public class RedisSentinelConfig {
    @Value("${spring.data.redis.timeout}")
    private int timeout;
    @Value("${spring.data.redis.password}")
    private String password;
    @Value("${spring.data.redis.sentinel.nodes}")
    private String redisNodes;
    @Value("${spring.data.redis.sentinel.master}")
    private String master;
    @Value("${spring.data.redis.pool.maxActive}")
    private int maxActive;
    @Value("${spring.data.redis.pool.maxWait}")
    private int maxWait;
    @Value("${spring.data.redis.pool.maxIdle}")
    private int maxIdle;
    @Value("${spring.data.redis.pool.maxIdle}")
    private int minIdle;
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        return jedisPoolConfig;
    }
    /**
     * redis哨兵配置
     */
    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration(){
        RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
        configuration.setMaster(master);
        String[] host = redisNodes.split(",");
        for(String redisHost : host){
            String[] item = redisHost.split(":");
            String ip = item[0];
            String port = item[1];
            configuration.addSentinel(new RedisNode(ip, Integer.parseInt(port)));
        }
        return configuration;
    }
    /**
     * 连接redis的工厂类
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory(redisSentinelConfiguration(),jedisPoolConfig());
        factory.setTimeout(timeout);
        factory.setPassword(password);
        factory.setTimeout(timeout);
        return factory;
    }

    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        //使用fastjson序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        // value值的序列化采用fastJsonRedisSerializer
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }

}
