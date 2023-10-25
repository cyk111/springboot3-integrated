package com.cyk.springboot3.integrated.mysql.mybatis.xml.controller;

import com.cyk.springboot3.integrated.mysql.mybatis.xml.common.Result;
import com.cyk.springboot3.integrated.mysql.mybatis.xml.controller.param.UsersQueryParam;
import com.cyk.springboot3.integrated.mysql.mybatis.xml.entity.User;
import com.cyk.springboot3.integrated.mysql.mybatis.xml.service.UserService;
import com.cyk.springboot3.integrated.mysql.mybatis.xml.service.dto.UserPageDTO;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/24 07:48
 */
@RestController
@RequestMapping(path = "/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @Operation(description = "根据id查询用户")
    @RequestMapping(path = "/getUserById",method = RequestMethod.GET)
    public Result<User> getUserById(@RequestParam Long id){
        User user = userService.selectById(id);
        return Result.success(user);
    }

    @Operation(description = "添加用户")
    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public Result<User> add(@RequestBody User user){
        userService.insert(user);
        return Result.success(user);
    }

    @Operation(description = "查询所用用户")
    @RequestMapping(path = "/selectAll",method = RequestMethod.GET)
    public Result<List<User>> selectAll(@RequestBody User user){
        return Result.success(userService.selectAll());
    }

    @Operation(description = "分页查询用户")
    @RequestMapping(path = "/page",method = RequestMethod.POST)
    public Result<PageInfo<User>> page(@Validated @NotNull @RequestBody UsersQueryParam usersQueryParam) {
        UserPageDTO dto = new UserPageDTO();
        BeanUtils.copyProperties(usersQueryParam, dto);
        PageInfo<User> result = userService.queryByPage(dto);
        return Result.success(result);
    }


}
