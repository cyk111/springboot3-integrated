package com.cyk.sprinbboot3.docker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/10/29 14:26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/test")
    public String test() {
        return "success";
    }

}
