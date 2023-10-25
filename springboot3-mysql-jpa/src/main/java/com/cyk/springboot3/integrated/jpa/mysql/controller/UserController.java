package com.cyk.springboot3.integrated.jpa.mysql.controller;

import com.cyk.springboot3.integrated.jpa.mysql.common.Result;
import com.cyk.springboot3.integrated.jpa.mysql.entity.User;
import com.cyk.springboot3.integrated.jpa.mysql.entity.query.UserQueryBean;
import com.cyk.springboot3.integrated.jpa.mysql.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author cyk
 * @date 2023/10/24 07:48
 */
@RestController
@RequestMapping(path = "/user")
@Slf4j
public class UserController {


    @Autowired
    private IUserService userService;

    /**
     * @param user user param
     * @return user
     */

    @Operation(summary = "Add/Edit Use")
    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public Result<User> add(@RequestBody User user) {
        if (user.getUserId()==null || !userService.exists(user.getUserId())) {
            userService.save(user);
        } else {
            userService.update(user);
        }
        return Result.success(userService.find(user.getUserId()));
    }


    @Operation(summary = "selectById")
    @RequestMapping(path = "/selectById/{userId}",method = RequestMethod.GET)
    public Result<User> selectById(@PathVariable("userId") Long userId) {
        return Result.success(userService.find(userId));
    }

    /**
     * @return user list
     */
    @Operation(summary = "page")
    @RequestMapping(path = "/page",method = RequestMethod.GET)
    public Result<Page<User>> page(@RequestParam int pageSize, @RequestParam int pageNumber) {
         return Result.success(userService.findPage(UserQueryBean.builder().build(), PageRequest.of(pageNumber, pageSize)));
    }



}
