package com.cyk.springboot3.integrated.jpa.mysql.service.impl;

import com.cyk.springboot3.integrated.jpa.mysql.entity.User;
import com.cyk.springboot3.integrated.jpa.mysql.entity.query.UserQueryBean;
import com.cyk.springboot3.integrated.jpa.mysql.jpa.dao.IBaseDao;
import com.cyk.springboot3.integrated.jpa.mysql.jpa.dao.IUserDao;
import com.cyk.springboot3.integrated.jpa.mysql.service.IUserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author cyk
 * @date 2023/10/24 08:07
 */
@Service
public class UserServiceImpl extends BaseDoServiceImpl<User, Long> implements IUserService {

    /**
     * userDao.
     */
    private final IUserDao userDao;

    /**
     * init.
     *
     * @param userDao2
     */
    public UserServiceImpl(final IUserDao userDao2) {
        this.userDao = userDao2;
    }

    /**
     * @return base dao
     */
    @Override
    public IBaseDao<User, Long> getBaseDao() {
        return this.userDao;
    }

    /**
     * find by page.
     *
     * @param queryBean   query
     * @param pageRequest pageRequest
     * @return page
     */
    @Override
    public Page<User> findPage(UserQueryBean queryBean, PageRequest pageRequest) {
        Specification<User> specification = new Specification<User>(){
            @Override
            public Specification<User> and(Specification<User> other) {
                return Specification.super.and(other);
            }

            @Override
            public Specification<User> or(Specification<User> other) {
                return Specification.super.or(other);
            }

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new LinkedList<>();
                //predicates.add(cb.equal(root.get("nickName"), queryBean.getName()));
                //predicates.add(cb.equal(root.get("age"), queryBean.getDescription()));
                return query.where(predicates.toArray(new Predicate[0])).getRestriction();
            }
        };

        return this.getBaseDao().findAll(specification, pageRequest);
    }

}
