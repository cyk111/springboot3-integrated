package com.cyk.springboot3.redis.lettuce.service;

import com.cyk.springboot3.redis.lettuce.entity.User;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/28 12:24
 */
public interface UserService {

    User getUserById(Long userId);

    int save(User user);

    int insert(User user);

    int delete(Long userId);

    int update(User record);

    List<User> selectAll();


}
