package com.cyk.springboot3.redis.lettuce.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cyk
 * @date 2023/10/28 14:02
 */
@Slf4j
@RestController
public class SessionController {



    // todo   session 优化 细化
    // 参考  https://blog.csdn.net/weixin_45780538/article/details/126136175

    /**
     * @param request
     * @return
     */
    @RequestMapping("/setSession")
    public Map<String, Object> setSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("sessionId",request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }


    /**
     * @param request
     * @return
     */
    @RequestMapping("/getSession")
    public Map<String, Object> getSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message",request.getSession().getAttribute("message"));
        return map;
    }
}
