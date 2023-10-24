package com.cyk.springboot3.integrated.i18N.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/10/24 14:10
 */
@RestController
@RequestMapping(path = "/test",name = "测试热部署")
public class TestHotDeployController {

    @Operation(summary = "测试热部署")
    @RequestMapping(path = "hotDeploy",method = RequestMethod.GET)
    public String getInfo(){
        System.out.println("========test info444");
        return "this is a test444 ..";
    }

    /**
     * 1 添加jar 包
     *
     * 2  IDEA 设置
     *         1. File->Setting->Build,Execution,Deployment->Compile  勾选：Make project automatically
     *         2. File->setting->Advanced Setttings里面的第一个设置  AllOW AUTO-make to start  even if ...
     * 3  配置文件
     *   spring:
     *      devtools:
     *          restart:
     *              enabled: true  #设置开启热部署
     *              additional-paths: src/main/java #重启目录
     *              exclude: WEB-INF/**
     */

}
