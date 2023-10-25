package com.cyk.springboot3.integrated.jpa.mysql.service.impl;

import com.cyk.springboot3.integrated.jpa.mysql.entity.BaseEntity;
import com.cyk.springboot3.integrated.jpa.mysql.jpa.dao.IBaseDao;
import com.cyk.springboot3.integrated.jpa.mysql.service.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * @author cyk
 * @date 2023/10/24 22:21
 */
public abstract class BaseDoServiceImpl <T extends BaseEntity, I extends Serializable> implements IBaseService<T, I> {

    /**
     * @return IBaseDao
     */
    public abstract IBaseDao<T, I> getBaseDao();


    @Override
    public T find(I id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public List<T> findList(I[] ids) {
        return null;
    }

    @Override
    public List<T> findList(Iterable<I> ids) {
        return null;
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return null;
    }

    @Override
    public T findOne(Specification<T> spec) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public long count(Specification<T> spec) {
        return 0;
    }

    @Override
    public boolean exists(I id) {
        return false;
    }

    @Override
    public void save(T entity) {

    }

    @Override
    public void save(List<T> entities) {

    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public void delete(I id) {

    }

    @Override
    public void deleteByIds(List<I> ids) {

    }

    @Override
    public void delete(T[] entities) {

    }

    @Override
    public void delete(Iterable<T> entities) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<T> findList(Specification<T> spec) {
        return null;
    }

    @Override
    public List<T> findList(Specification<T> spec, Sort sort) {
        return null;
    }

    @Override
    public void flush() {

    }
}
