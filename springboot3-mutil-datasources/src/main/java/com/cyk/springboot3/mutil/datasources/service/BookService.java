package com.cyk.springboot3.mutil.datasources.service;

import com.cyk.springboot3.mutil.datasources.entity.Book;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/26 19:10
 */
public interface BookService {

    List<Book> selectAll();
}
