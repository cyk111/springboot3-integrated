package com.cyk.springboot3.mail.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/10/29 07:38
 */
@RestController
@Slf4j
public class MailController {
    @Resource
    private JavaMailSender sender;

    @GetMapping("/send")
    public void send() {
        //SimpleMailMessage是一个比较简易的邮件封装，支持设置一些比较简单内容
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件标题
        message.setSubject("[测试] 这是一个测试邮件");
        //设置邮件内容
        message.setText("这是一封测试邮件。");
        //设置邮件发送给谁，可以多个，这里就发给我的QQ邮箱
        message.setTo("chenyongke***@163.com");
        //邮件发送者，这里要与配置文件中的保持一致
        message.setFrom("chenyongke***@163.com");
        //发送
        try {
            sender.send(message) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            log.info(e.getMessage());
        }

    }
}
