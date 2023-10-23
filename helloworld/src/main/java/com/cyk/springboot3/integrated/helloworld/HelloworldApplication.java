package com.cyk.springboot3.integrated.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/10/24 06:49
 */
@SpringBootApplication
@RestController
public class HelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }

    @GetMapping("/helloworld")
    public String getInfo(){
        System.out.println("hello world !!!");
        return "hello world !!!";
    }

}
