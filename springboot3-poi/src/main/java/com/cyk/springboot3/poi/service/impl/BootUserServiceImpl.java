package com.cyk.springboot3.poi.service.impl;

import com.cyk.springboot3.poi.entity.BootUser;
import com.cyk.springboot3.poi.mapper.BootUserMapper;
import com.cyk.springboot3.poi.service.BootUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/30 21:52
 */
@Service
public class BootUserServiceImpl implements BootUserService {

    @Resource
    private BootUserMapper bootUserMapper;

    @Override
    public int save(BootUser bootUser) {
        return bootUserMapper.insert(bootUser);
    }

    @Override
    public List<BootUser> list() {
        return bootUserMapper.list();
    }
}
