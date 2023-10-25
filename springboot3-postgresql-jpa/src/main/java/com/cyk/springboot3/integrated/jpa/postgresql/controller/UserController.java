package com.cyk.springboot3.integrated.jpa.postgresql.controller;

import com.cyk.springboot3.integrated.jpa.postgresql.common.Result;
import com.cyk.springboot3.integrated.jpa.postgresql.entity.User;
import com.cyk.springboot3.integrated.jpa.postgresql.entity.query.UserQueryBean;
import com.cyk.springboot3.integrated.jpa.postgresql.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
        if (user.getId()==null || !userService.exists(user.getId())) {
            user.setPassword("123456");
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userService.save(user);
        } else {
            user.setUpdateTime(LocalDateTime.now());
            userService.update(user);
        }
        return Result.success(userService.find(user.getId()));
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
    public Result<Page<User>> page(@RequestParam int pageSize, @RequestParam int pageNumber,@RequestBody UserQueryBean queryBean) {
         return Result.success(userService.findPage(queryBean,PageRequest.of(pageNumber, pageSize)));
    }



}
