package com.cyk.springboot3.mutil.datasources.service.impl;

import com.cyk.springboot3.mutil.datasources.entity.Book;
import com.cyk.springboot3.mutil.datasources.mapper2.BookMapper;
import com.cyk.springboot3.mutil.datasources.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/26 19:10
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;
    @Override
    public List<Book> selectAll() {
        return bookMapper.selectAll();
    }
}
