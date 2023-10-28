package com.cyk.springboot3.redis.lettuce.service.impl;

import com.cyk.springboot3.redis.lettuce.constant.RedisConstants;
import com.cyk.springboot3.redis.lettuce.entity.User;
import com.cyk.springboot3.redis.lettuce.mapper.UserMapper;
import com.cyk.springboot3.redis.lettuce.service.UserService;
import com.cyk.springboot3.redis.lettuce.utils.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/28 12:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional
    public int save(User user) {
        int i = userMapper.insert(user);
        redisUtil.set(RedisConstants.REDIS_KEY_PREFIX_USER+user.getUserId(),user, 600);
        return i ;
    }

    @Override
    public int delete(Long userId) {
        int i = userMapper.deleteByPrimaryKey(userId);
        redisUtil.del(RedisConstants.REDIS_KEY_PREFIX_USER+userId);
        return i;
    }

    @Override
    public int update(User record) {
        int i = userMapper.updateByPrimaryKey(record);
        redisUtil.set(RedisConstants.REDIS_KEY_PREFIX_USER+record.getUserId(),record);
        return i;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
