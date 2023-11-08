package com.cyk.springboot3.redis.cluster.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import io.lettuce.core.ReadFrom;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

/**
 * @author cyk
 * @date 2023/11/3 11:21
 */
@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.password}")
    private String password;

    @Value("${spring.data.redis.cluster.nodes}")
    private List<String> nodes;

    @Bean("redisConnectionFactory")
    public LettuceConnectionFactory lettuceConnectionFactory() {
        // 集群配置
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        for (String node : nodes) {
            String[] arr = node.split(":");
            clusterConfiguration.addClusterNode(new RedisNode(arr[0], Integer.parseInt(arr[1])));
        }
        clusterConfiguration.setPassword(RedisPassword.of(password));
        return new LettuceConnectionFactory(clusterConfiguration);
    }

    @Bean("redisConnectionFactoryReadOnly")
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.SLAVE_PREFERRED)
                .build();
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        for (String node : nodes) {
            String[] arr = node.split(":");
            clusterConfiguration.addClusterNode(new RedisNode(arr[0], Integer.parseInt(arr[1])));
        }
        clusterConfiguration.setPassword(RedisPassword.of(password));

        return new LettuceConnectionFactory(clusterConfiguration, clientConfig);
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(@Qualifier("redisConnectionFactory") LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        //使用fastjson序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        // value值的序列化采用fastJsonRedisSerializer
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
