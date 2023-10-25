package com.cyk.springboot3.integrated.jpa.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author cyk
 * @date 2023/10/24 07:42
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.cyk.springboot3.integrated.jpa.mysql.entity"})
public class MysqlJPAApplication {
    public static void main(String[] args) {
        SpringApplication.run(MysqlJPAApplication.class,args);
    }
}
