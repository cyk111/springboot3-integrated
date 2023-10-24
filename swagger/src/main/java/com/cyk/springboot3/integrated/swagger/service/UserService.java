package com.cyk.springboot3.integrated.swagger.service;

import com.cyk.springboot3.integrated.swagger.controller.param.UserParam;
import com.cyk.springboot3.integrated.swagger.entity.User;

/**
 * @author cyk
 * @date 2023/10/24 07:54
 */
public interface UserService {

    public User getUserInfo(Long id);

    public User getUserByName(String userName);

    public User getUserByNameAndAge(UserParam param);

}
