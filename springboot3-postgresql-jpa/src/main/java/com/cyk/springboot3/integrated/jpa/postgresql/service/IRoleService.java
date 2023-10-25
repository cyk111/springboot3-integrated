package com.cyk.springboot3.integrated.jpa.postgresql.service;

import com.cyk.springboot3.integrated.jpa.postgresql.entity.Role;
import com.cyk.springboot3.integrated.jpa.postgresql.entity.query.RoleQueryBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/24 22:32
 */
public interface IRoleService extends IBaseService<Role, Long> {

    @Override
    Role find(Long id);

    @Override
    List<Role> findAll();

    Page<Role> findPage(RoleQueryBean roleQueryBean, PageRequest pageRequest);
}
