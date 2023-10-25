package com.cyk.springboot3.integrated.jpa.mysql.service.impl;

import com.cyk.springboot3.integrated.jpa.mysql.entity.Role;
import com.cyk.springboot3.integrated.jpa.mysql.entity.query.RoleQueryBean;
import com.cyk.springboot3.integrated.jpa.mysql.jpa.dao.IBaseDao;
import com.cyk.springboot3.integrated.jpa.mysql.jpa.dao.IRoleDao;
import com.cyk.springboot3.integrated.jpa.mysql.service.IRoleService;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author cyk
 * @date 2023/10/24 22:36
 */
public class RoleServiceImpl extends BaseDoServiceImpl<Role, Long> implements IRoleService {

    /**
     * roleDao.
     */
    private final IRoleDao roleDao;

    /**
     * init.
     *
     * @param roleDao2 role dao
     */
    public RoleServiceImpl(final IRoleDao roleDao2) {
        this.roleDao = roleDao2;
    }

    /**
     * @return base dao
     */
    @Override
    public IBaseDao<Role, Long> getBaseDao() {
        return this.roleDao;
    }

    /**
     * find page by query.
     *
     * @param roleQueryBean query
     * @param pageRequest   pageRequest
     * @return page
     */
    @Override
    public Page<Role> findPage(RoleQueryBean roleQueryBean, PageRequest pageRequest) {
        Specification<Role> specification = Specifications.<Role>and()
                .like(StringUtils.isNotEmpty(roleQueryBean.getName()), "name",
                        roleQueryBean.getName())
                .like(StringUtils.isNotEmpty(roleQueryBean.getDescription()), "description",
                        roleQueryBean.getDescription())
                .build();
        return this.roleDao.findAll(specification, pageRequest);
    }
}
