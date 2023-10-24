package com.cyk.springboot3.integrated.i18N;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cyk
 * @date 2023/10/24 07:42
 */
@SpringBootApplication
@ComponentScan({"com.cyk.springboot3.integrated.i18N.controller","com.cyk.springboot3.integrated.i18N.service"})
public class I18NApplication {
    public static void main(String[] args) {
        SpringApplication.run(I18NApplication.class,args);
    }

}
