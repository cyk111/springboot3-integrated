package com.cyk.springboot3.integrated.helloworld.mvc.service;

import com.cyk.springboot3.integrated.helloworld.mvc.common.GenderEnums;
import com.cyk.springboot3.integrated.helloworld.mvc.controller.param.UserParam;
import com.cyk.springboot3.integrated.helloworld.mvc.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author cyk
 * @date 2023/10/24 08:07
 */
@Service
public class UserServiceImpl implements UserService{

    @Override
    public User getUserInfo(Long id) {
        User user = new User();
        if(id == 1){
            user.setId(1L);
            user.setName("zhangsan");
            user.setAddress("bj");
            user.setAge(18);
            user.setEmail("zs@gmail.com");
            user.setGender(GenderEnums.MAN.getValue());
            user.setPhone("15133445566");
            user.setPassword("abcdefg");
        }else if(id ==2){
            user.setId(2L);
            user.setName("lisi");
            user.setAddress("shanghai");
            user.setAge(24);
            user.setEmail("ls@gmail.com");
            user.setGender(GenderEnums.WOMEN.getValue());
            user.setPhone("15199887766");
            user.setPassword("qwertyu");
        }
        return user;
    }

    @Override
    public User getUserByName(String userName) {
        User user = new User();
        user.setId(1L);
        user.setName("zhangsan");
        user.setAddress("bj");
        user.setAge(18);
        user.setEmail("zs@gmail.com");
        user.setGender(GenderEnums.MAN.getValue());
        user.setPhone("15133445566");
        user.setPassword("abcdefg");
        return user;
    }

    @Override
    public User getUserByNameAndAge(UserParam param) {
        User user = new User();
        if(param.getUserName().equals("wangwu") && param.getAge().intValue() == 16){
            user.setId(3L);
            user.setName("wangwu");
            user.setAddress("guangzhou");
            user.setAge(18);
            user.setEmail("wangwu@gmail.com");
            user.setGender(GenderEnums.MAN.getValue());
            user.setPhone("15155889900");
            user.setPassword("zxcvbnmn");
        }else {
            user.setId(1L);
            user.setName("zhangsan");
            user.setAddress("bj");
            user.setAge(18);
            user.setEmail("zs@gmail.com");
            user.setGender(GenderEnums.MAN.getValue());
            user.setPhone("15133445566");
            user.setPassword("abcdefg");
        }
        return user;
    }
}
