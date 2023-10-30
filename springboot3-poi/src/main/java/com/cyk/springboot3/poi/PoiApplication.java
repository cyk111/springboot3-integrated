package com.cyk.springboot3.poi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyk
 * @date 2023/10/30 21:35
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.cyk.springboot3.poi.mapper"})
public class PoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoiApplication.class,args);
    }
}
