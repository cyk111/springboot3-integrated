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

    // 参考文档 https://yaoyuanyy.github.io/gitbook/gitbook_web/%E8%BF%90%E7%BB%B4/2019-04-08_springboot-docker.html
    // 参考文档 https://yaoyuanyy.github.io/2019/05/22/springboot%E9%9B%86%E6%88%90docker%E5%AE%9E%E4%BE%8B/index.html
    // https://github.com/gf-huanchupk/SpringBootLearning

    /**
     * docker 优点
     * 1.简化部署过程，提升可移植性
     * 2.软件升级和版本控制
     * 3.隔离性
     *
     * 命令：
     * - docker  commit
     * - docker build
     * -
     *
     *
     *
     */




    @RequestMapping("/test")
    public String test() {
        return "success";
    }

}
