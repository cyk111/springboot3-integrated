package com.cyk.springboot3.integrated.helloworld.mvc.service;

import com.cyk.springboot3.integrated.helloworld.mvc.controller.param.UserParam;
import com.cyk.springboot3.integrated.helloworld.mvc.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author cyk
 * @date 2023/10/24 07:54
 */
public interface UserService {

    public User getUserInfo(Long id);

    public User getUserByName(String userName);

    public User getUserByNameAndAge(UserParam param);

}
