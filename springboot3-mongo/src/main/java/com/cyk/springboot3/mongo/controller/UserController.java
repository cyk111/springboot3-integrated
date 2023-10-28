package com.cyk.springboot3.mongo.controller;

import com.cyk.springboot3.mongo.entity.User;
import com.cyk.springboot3.mongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/10/28 07:59
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public void testSaveUser(){
        User user =new User(2l,"zhangtiegang","gougang");
        userRepository.saveUser(user);
    }

    @RequestMapping(value = "/findUserByUserName",method = RequestMethod.GET)
    public void findUserByUserName(){
        User user= userRepository.findUserByUserName("zhangtiegang");
        System.out.println("user is "+user);
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.GET)
    public void updateUser(){
        User user =new User(2l,"wangchunhua","chuanhua");
        userRepository.updateUser(user);
    }

    @RequestMapping(value = "/testDeleteUserById",method = RequestMethod.GET)
    public void testDeleteUserById(){
        userRepository.deleteUserById(2l);
    }
}
