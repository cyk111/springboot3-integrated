package com.cyk.springboot3.multi.version.controller;

import com.cyk.springboot3.multi.version.annotation.ApiVersion;
import com.cyk.springboot3.multi.version.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/11/6 15:44
 */
@RestController
@RequestMapping("api/{v}/user")
public class UserController {

    @RequestMapping("get")
    public User getUser() {
        return User.builder().age(18).name("pdai, default").build();
    }

    @ApiVersion("1.0.0")
    @RequestMapping("get")
    public User getUserV1() {
        return User.builder().age(18).name("pdai, v1.0.0").build();
    }

    @ApiVersion("1.1.0")
    @RequestMapping("get")
    public User getUserV11() {
        return User.builder().age(19).name("pdai, v1.1.0").build();
    }

    @ApiVersion("1.1.2")
    @RequestMapping("get")
    public User getUserV112() {
        return User.builder().age(19).name("pdai2, v1.1.2").build();
    }
}
