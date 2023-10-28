package com.cyk.springboot3.redis.lettuce.config;

/**
 * @author cyk
 * @date 2023/10/28 13:59
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
public class SessionConfig {

    //maxInactiveIntervalInSeconds: 设置 Session 失效时间，使用 Redis Session 之后，
    //原 Spring Boot 中的 server.session.timeout 属性不再生效。
}
