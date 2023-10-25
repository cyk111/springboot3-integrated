package com.cyk.springboot3.integrated.jpa.postgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author cyk
 * @date 2023/10/24 07:42
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.cyk.springboot3.integrated.jpa.postgresql.entity"})
public class PostgresqlJPAApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostgresqlJPAApplication.class,args);
    }
}
