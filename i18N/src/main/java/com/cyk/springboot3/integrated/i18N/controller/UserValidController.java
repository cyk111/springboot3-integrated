package com.cyk.springboot3.integrated.i18N.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @author cyk
 * @date 2023/10/24 14:51
 */
@RestController
@RequestMapping(path = "/userValid",name = "校验参数信息")
public class UserValidController {

    private final MessageSource messageSource;

    public UserValidController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(path = "/message",method = RequestMethod.GET)
    public  String addUserValid() {
        Locale locale = LocaleContextHolder.getLocale();
        String userName = messageSource.getMessage("user.name", null, locale);
        return userName;
    }


   //  @Validated和@Valid什么区别
   //  检验举例
   //  分组校验  嵌套校验
   //   统一异常处理


}
