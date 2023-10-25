package com.cyk.springboot3.integrated.mysql.mybatis.xml.service;

import com.cyk.springboot3.integrated.mysql.mybatis.xml.entity.User;
import com.cyk.springboot3.integrated.mysql.mybatis.xml.service.dto.UserPageDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/25 14:57
 */
public interface UserService {

    User selectById(Long id);

    int insert(User user);

    List<User> selectAll();

    PageInfo<User> queryByPage(UserPageDTO userPageDTO);



}
