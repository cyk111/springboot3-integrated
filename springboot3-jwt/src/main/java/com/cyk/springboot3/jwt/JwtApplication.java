package com.cyk.springboot3.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyk
 * @date 2023/10/30 22:41
 */
@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class,args);
    }

    /***
     * 测试方法
     * # http://localhost:8080/login?userName=zhangsan&password=123456
     * 生成jwt 字符串
     *
     * 验证
     * http://localhost:8080/secure/getUserInfo
     *
     *  header 添加 jwt信息
     *
     *  key: Authorization
     *  value: jwt 字符串
     *
     *
     *
     *
     *
     *
     */
}
