package com.cyk.springboot3.log.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/10/29 09:02
 */
@RestController
public class LogController {

    /**
     * 目前存在很多日志框架：JCL、SLF4J、log4j、logback
     * 选择一个日志抽象层框架+日志实现，类似我们使用JDBC选择不同的数据驱动
     * SpringBoot日志默认设置：
     * SLF4J+Logback
     * 默认值输出到控制台
     * 默认级别Level为INFO
     * 默认日志文件大小为10M
     * 输出格式为
     * 2019-03-05 10:57:51.702 INFO 45469 --- [ost-startStop-1] osbcembedded.FilterRegistrationBean：映射过滤器：'hiddenHttpMethodFilter' 到：[/*]
     * 输出以下项目：
     * 日期和时间：毫秒精度，易于排序。
     * 日志级别：ERROR、WARN、INFO、DEBUG或TRACE。
     * 进程标识。
     * ---用于区分实际日志消息开始的分隔符。
     * 线程名称：括在方括号中（可能会被截断以用于控制台输出）。
     * 记录器名称：这通常是源类名称（通常缩写）。
     * 日志消息
     *
     */


    /**
     * 支持日志路径，日志level等配置
     * 日志控制配置通过application.yml下发按天生成日志，
     * 当天的日志>50MB回滚
     * 最多保存10天日志
     * 生成的日志中Pattern自定义
     * Pattern中添加用户自定义的MDC字段，比如用户信息(当前日志是由哪个用户的请求产生)，request信息。此种方式可以通过AOP切面控制，在MDC中添加requestID，在spring-logback.xml中配置Pattern。
     * 根据不同的运行环境设置Profile - dev，test，product
     * 对控制台，Err和全量日志分别配置对第三方包路径日志控制#
     * ------
     * 著作权归@pdai所有
     * 原文链接：https://pdai.tech/md/spring/springboot/springboot-x-hello-logback.html
     */

    Logger logger = LoggerFactory.getLogger(LogController.class);
    @RequestMapping("/log")
    public String index(){
        //由低到高 trace<debug<info<warn<error
        //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        logger.trace("trace级别的日志");
        logger.debug("debug级别的日志");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.info("info级别的日志");
        logger.warn("warn级别的日志");
        logger.error("error级别的日志");

        return  "logger info ";
    }
}
