package com.cyk.springboot3.poi.service;

import com.cyk.springboot3.poi.entity.BootUser;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/30 21:51
 */
public interface BootUserService {

    int save(BootUser bootUser);

    List<BootUser> list();
}
