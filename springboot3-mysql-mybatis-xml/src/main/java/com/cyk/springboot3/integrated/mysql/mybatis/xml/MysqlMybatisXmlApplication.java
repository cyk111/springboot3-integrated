package com.cyk.springboot3.integrated.mysql.mybatis.xml;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyk
 * @date 2023/10/24 07:42
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.cyk.springboot3.integrated.mysql.mybatis.xml.mapper"})
public class MysqlMybatisXmlApplication {
    public static void main(String[] args) {
        SpringApplication.run(MysqlMybatisXmlApplication.class,args);
    }
}
