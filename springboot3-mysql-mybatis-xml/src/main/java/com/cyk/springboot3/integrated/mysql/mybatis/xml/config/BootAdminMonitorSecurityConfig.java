package com.cyk.springboot3.integrated.mysql.mybatis.xml.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author cyk
 * @date 2023/10/29 06:55
 */
@Configuration
public class BootAdminMonitorSecurityConfig {
    /**
     * 增加 springboot 服务端安全验证 解决客户端注册报401
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.anyRequest().permitAll())
                .csrf().disable().build();
    }
}
