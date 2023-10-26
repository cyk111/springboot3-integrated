package com.cyk.springboot3.mutil.datasources.service.impl;

import com.cyk.springboot3.integrated.mysql.mybatis.xml.service.dto.UserPageDTO;
import com.cyk.springboot3.mutil.datasources.entity.User;
import com.cyk.springboot3.mutil.datasources.mapper1.UserMapper;
import com.cyk.springboot3.mutil.datasources.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/25 14:57
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }


    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public PageInfo<User> queryByPage(UserPageDTO userPageDTO) {
        PageHelper.startPage(userPageDTO.getPageNo(), userPageDTO.getPageSize());
        Page page = userMapper.queryByPage(userPageDTO);
        PageInfo<User> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }
}
