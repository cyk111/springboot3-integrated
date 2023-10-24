package com.cyk.springboot3.integrated.helloworld.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/10/24 07:42
 */
@SpringBootApplication
@RestController
public class HelloWorldmvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldmvcApplication.class,args);
    }

    @GetMapping("/hello")
    public String helloworld(){
        return "helloworld";
    }
}
