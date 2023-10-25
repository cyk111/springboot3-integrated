package com.cyk.springboot3.integrated.jpa.postgresql.service;

import com.cyk.springboot3.integrated.jpa.postgresql.entity.User;
import com.cyk.springboot3.integrated.jpa.postgresql.entity.query.UserQueryBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author cyk
 * @date 2023/10/24 07:54
 */
public interface IUserService extends IBaseService<User, Long> {

    /**
     * find by page.
     *
     * @param userQueryBean query
     * @param pageRequest   pageRequest
     * @return page
     */
    Page<User> findPage(UserQueryBean userQueryBean, PageRequest pageRequest);


}
