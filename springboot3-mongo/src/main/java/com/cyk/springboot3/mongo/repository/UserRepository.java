package com.cyk.springboot3.mongo.repository;

import com.cyk.springboot3.mongo.entity.User;

/**
 * @author cyk
 * @date 2023/10/28 07:57
 */
public interface UserRepository {

    public void saveUser(User user);

    public User findUserByUserName(String userName);

    public long updateUser(User user);

    public void deleteUserById(Long id);
}
