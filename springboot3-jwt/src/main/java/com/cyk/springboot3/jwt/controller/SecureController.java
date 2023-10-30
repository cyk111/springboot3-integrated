package com.cyk.springboot3.jwt.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/10/30 22:56
 */

@Slf4j
@RestController
public class SecureController {

    //springboot 配置过滤器不起作用的原因
    // 1.当前过滤器类上要加两个注解
    // @Component
    // @WebFilter(filterName="一般写类名",urlPatterns="/*")
    // 2.启动类上要加多一个注解
    //  @ServletComponentScan

    /**
     * 查询 用户信息，登录后携带JWT才能访问
     */
    @RequestMapping("/secure/getUserInfo")
    public String login(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("id");
        String userName = request.getAttribute("userName").toString();
        String password= request.getAttribute("password").toString();
        return "当前用户信息id=" + id + ",userName=" + userName+ ",password=" + password;
    }
}
