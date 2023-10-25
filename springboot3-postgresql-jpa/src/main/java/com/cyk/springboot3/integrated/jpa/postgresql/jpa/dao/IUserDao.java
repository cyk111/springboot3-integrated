package com.cyk.springboot3.integrated.jpa.postgresql.jpa.dao;

import com.cyk.springboot3.integrated.jpa.postgresql.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author cyk
 * @date 2023/10/24 22:15
 */
@Repository
public interface IUserDao extends IBaseDao<User,Long> {
}
