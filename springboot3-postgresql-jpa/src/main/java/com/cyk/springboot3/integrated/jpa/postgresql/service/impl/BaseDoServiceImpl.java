package com.cyk.springboot3.integrated.jpa.postgresql.service.impl;

import com.cyk.springboot3.integrated.jpa.postgresql.entity.BaseEntity;
import com.cyk.springboot3.integrated.jpa.postgresql.jpa.dao.IBaseDao;
import com.cyk.springboot3.integrated.jpa.postgresql.service.IBaseService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author cyk
 * @date 2023/10/24 22:21
 */
@Slf4j
@Transactional
public abstract class BaseDoServiceImpl <T extends BaseEntity, I extends Serializable> implements IBaseService<T, I> {

    /**
     * @return IBaseDao
     */
    public abstract IBaseDao<T, I> getBaseDao();


    @Override
    public T find(I id) {
        return getBaseDao().findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return getBaseDao().findAll();
    }

    @Override
    public List<T> findList(I[] ids) {
        List<I> idList = Arrays.asList(ids);
        return getBaseDao().findAllById(idList);
    }

    @Override
    public List<T> findList(Iterable<I> ids) {
        return getBaseDao().findAllById(ids);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getBaseDao().findAll(pageable);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return getBaseDao().findAll(spec, pageable);
    }

    @Override
    public T findOne(Specification<T> spec) {
        return getBaseDao().findOne(spec).orElse(null);
    }

    @Override
    public long count() {
        return getBaseDao().count();
    }

    @Override
    public long count(Specification<T> spec) {
        return getBaseDao().count(spec);
    }

    @Override
    public boolean exists(I id) {
        return getBaseDao().findById(id).isPresent();
    }

    @Override
    public void save(T entity) {
        getBaseDao().save(entity);
    }

    @Override
    public void save(List<T> entities) {
        getBaseDao().saveAll(entities);
    }

    @Override
    public T update(T entity) {
        return getBaseDao().saveAndFlush(entity);
    }

    @Override
    public void delete(I id) {
        getBaseDao().deleteById(id);
    }

    @Override
    public void deleteByIds(List<I> ids) {
        getBaseDao().deleteAllById(ids);
    }

    @Override
    public void delete(T[] entities) {
        List<T> tList = Arrays.asList(entities);
        getBaseDao().deleteAll(tList);
    }

    @Override
    public void delete(Iterable<T> entities) {
        getBaseDao().deleteAll(entities);
    }

    @Override
    public void delete(T entity) {
        getBaseDao().delete(entity);
    }

    @Override
    public void deleteAll() {
        getBaseDao().deleteAll();
    }

    @Override
    public List<T> findList(Specification<T> spec) {
        return getBaseDao().findAll(spec);
    }

    @Override
    public List<T> findList(Specification<T> spec, Sort sort) {
        return getBaseDao().findAll(spec, sort);
    }

    @Override
    public void flush() {
        getBaseDao().flush();
    }
}
