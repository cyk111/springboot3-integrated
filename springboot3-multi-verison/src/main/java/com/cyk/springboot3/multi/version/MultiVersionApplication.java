package com.cyk.springboot3.multi.version;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyk
 * @date 2023/11/6 15:29
 */
@SpringBootApplication
public class MultiVersionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiVersionApplication.class,args);
    }

    /***
     * 接口多版本
     *
     * 为什么会出现多版本?
     *   一般restful接口是提供其他模块调用，系统在其他公司部门使用时候不能频繁变更，需求业务变更，接口参数会有相应的变化,如果对原来的接口进行修改，会影响到其他啊
     *   系统的正常运行，所以需要对接口进行多版本控制
     *
     * 控制接口版本的方式:
     *    1.形同的URL,用不同的版本参数区分
     *    2.区分不同接口的域名
     *    3.网关路由不同子目录到不同的实例
     *    4.同一实例，用注解隔离不同版本控制
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
}
