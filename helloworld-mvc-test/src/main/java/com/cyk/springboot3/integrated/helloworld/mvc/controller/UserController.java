package com.cyk.springboot3.integrated.helloworld.mvc.controller;

import com.cyk.springboot3.integrated.helloworld.mvc.common.Result;
import com.cyk.springboot3.integrated.helloworld.mvc.controller.param.UserParam;
import com.cyk.springboot3.integrated.helloworld.mvc.controller.vo.UserVO;
import com.cyk.springboot3.integrated.helloworld.mvc.entity.User;
import com.cyk.springboot3.integrated.helloworld.mvc.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cyk
 * @date 2023/10/24 07:48
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * @PathVariable 与 @RequestParam 区别
     * 分别是从路径里面去获取变量，
     * PathVariable 也就是把路径当做变量
     * RequestParam 后者是从请求里面获取参数
     *
     *  @RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)
     *  而最常用的使用请求体传参的无疑是POST请求了，所以使用@RequestBody接收数据时，一般都用POST方式进行提交。
     *  在后端的同一个接收方法里，@RequestBody与@RequestParam()可以同时使用，
     *  @RequestBody最多只能有一个，而@RequestParam()可以有多个
     *  RequestBody 接收的是请求体里面的数据；而RequestParam接收的是key-value
     *  里面的参数
     */

    @RequestMapping(path = "/getById/{id}",method = RequestMethod.GET)
    public User getUserInfo(@PathVariable("id") Long id){
        User user = userService.getUserInfo(id);
        return user;
    }


    @RequestMapping(path = "/getUserInfoResult/{id}",method = RequestMethod.GET)
    public Result<User> getUserInfoResult(@PathVariable("id") Long id){
        User user = userService.getUserInfo(id);
        return Result.success(user,"获取成功");
    }



    @RequestMapping(path = "/getById",method = RequestMethod.GET)
    public User getUserInfoParam(@RequestParam("id") Long id){
        User user = userService.getUserInfo(id);
        return user;
    }

    @RequestMapping(path = "/getByUserName",method = RequestMethod.GET)
    public UserVO getUserByName(@RequestParam("userName") String userName){
        User user = userService.getUserByName(userName);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    @RequestMapping(path = "/getByUserName1",method = RequestMethod.GET)
    public UserVO getUserByName(@RequestParam("userName") String userName,@RequestParam("age") Integer age){
        UserVO userVO = new UserVO();
        if(age==1){
            User user = userService.getUserByName(userName);
            BeanUtils.copyProperties(user,userVO);
        }
        return userVO;
    }

    @RequestMapping(path = "/getByNameAndAge",method = RequestMethod.POST)
    public UserVO getByNameAndAge(@RequestBody UserParam userName){
        User user = userService.getUserByNameAndAge(userName);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }


}
