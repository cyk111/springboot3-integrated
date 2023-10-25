package com.cyk.springboot3.integrated.jpa.postgresql.jpa.dao;

import com.cyk.springboot3.integrated.jpa.postgresql.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author cyk
 * @date 2023/10/24 22:14
 */
@NoRepositoryBean
public interface IBaseDao <T extends BaseEntity, I extends Serializable> extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {

}
