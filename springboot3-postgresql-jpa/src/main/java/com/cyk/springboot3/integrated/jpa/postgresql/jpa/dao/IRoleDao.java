package com.cyk.springboot3.integrated.jpa.postgresql.jpa.dao;

import com.cyk.springboot3.integrated.jpa.postgresql.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * @author cyk
 * @date 2023/10/24 22:16
 */
@Repository
public interface IRoleDao extends IBaseDao<Role,Long>{
}
