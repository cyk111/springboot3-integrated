package com.cyk.springboot3.integrated.jpa.postgresql.service.impl;

import com.cyk.springboot3.integrated.jpa.postgresql.entity.Role;
import com.cyk.springboot3.integrated.jpa.postgresql.entity.query.RoleQueryBean;
import com.cyk.springboot3.integrated.jpa.postgresql.jpa.dao.IBaseDao;
import com.cyk.springboot3.integrated.jpa.postgresql.jpa.dao.IRoleDao;
import com.cyk.springboot3.integrated.jpa.postgresql.service.IRoleService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
        Specification<Role> specification = new Specification<Role>(){
            @Override
            public Specification<Role> and(Specification<Role> other) {
                return Specification.super.and(other);
            }

            @Override
            public Specification<Role> or(Specification<Role> other) {
                return Specification.super.or(other);
            }

            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        return this.roleDao.findAll(specification, pageRequest);
    }
}
