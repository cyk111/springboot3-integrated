package com.cyk.springboot3.integrated.jpa.mysql.service;

import com.cyk.springboot3.integrated.jpa.mysql.entity.User;
import com.cyk.springboot3.integrated.jpa.mysql.entity.query.UserQueryBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author cyk
 * @date 2023/10/24 07:54
 */
public interface IUserService extends IBaseService<User, Long> {


    @Override
    User find(Long id);

    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    void save(User entity);

    @Override
    User update(User entity);

    @Override
    void delete(Long id);

    /**
     * find by page.
     *
     * @param userQueryBean query
     * @param pageRequest   pageRequest
     * @return page
     */
    Page<User> findPage(UserQueryBean userQueryBean, PageRequest pageRequest);


}
