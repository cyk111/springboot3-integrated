package com.cyk.springboot3.integrated.jpa.mysql.service.impl;

import com.cyk.springboot3.integrated.jpa.mysql.entity.User;
import com.cyk.springboot3.integrated.jpa.mysql.entity.query.UserQueryBean;
import com.cyk.springboot3.integrated.jpa.mysql.jpa.dao.IBaseDao;
import com.cyk.springboot3.integrated.jpa.mysql.jpa.dao.IUserDao;
import com.cyk.springboot3.integrated.jpa.mysql.service.IUserService;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
     * @param userDao
     */
    public UserServiceImpl(final IUserDao userDao) {
        this.userDao = userDao;
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
        Specification<User> specification = Specifications.<User>and()
                .like(StringUtils.isNotEmpty(queryBean.getName()), "user_name", queryBean.getName())
                .like(StringUtils.isNotEmpty(queryBean.getDescription()), "description",
                        queryBean.getDescription())
                .build();
        return this.getBaseDao().findAll(specification, pageRequest);
    }

}
