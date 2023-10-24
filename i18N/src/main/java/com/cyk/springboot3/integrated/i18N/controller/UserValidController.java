package com.cyk.springboot3.integrated.i18N.controller;

import com.cyk.springboot3.integrated.i18N.common.Code;
import com.cyk.springboot3.integrated.i18N.common.Result;
import com.cyk.springboot3.integrated.i18N.controller.param.UserValidParam;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/10/24 14:51
 */
@RestController
@RequestMapping(path = "userValid",name = "校验参数信息")
@Validated
public class UserValidController {


    /**
     * 没有参数校验
     * @param userValidParam
     * @return
     */
    @RequestMapping(path = "/addUserUnValid",method = RequestMethod.POST,name = "添加用户")
    public Result<String> addUserUnValid(@Valid @RequestBody UserValidParam userValidParam) {
        if(userValidParam.getName()==null) {
            return Result.failure(Code.SYSTEM_ERROR,"user name should not be empty");
        } else if(userValidParam.getName().length()<5 || userValidParam.getName().length()>50){
            return Result.failure(Code.SYSTEM_ERROR,"user name length should between 5-50");
        }
        if(userValidParam.getAge()< 1 || userValidParam.getAge()> 150) {
            return Result.failure(Code.SYSTEM_ERROR,"invalid age");
        }
        return Result.success("success");
    }

    /**
     *
     */

    @RequestMapping(path = "/addUserValid",method = RequestMethod.POST,name = "添加用户-校验")
    public Result<String> addUserValid(@Valid @RequestBody UserValidParam userValidParam) {
        // 业务逻辑处理
        String msg = "ok";
        return Result.success(msg);
    }


   //  @Validated和@Valid什么区别
   //  检验举例
   //  分组校验  嵌套校验
   //   统一异常处理


}
