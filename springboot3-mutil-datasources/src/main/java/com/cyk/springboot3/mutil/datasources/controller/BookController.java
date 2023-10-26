package com.cyk.springboot3.mutil.datasources.controller;

import com.cyk.springboot3.mutil.datasources.common.Result;
import com.cyk.springboot3.mutil.datasources.entity.Book;
import com.cyk.springboot3.mutil.datasources.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/26 19:08
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/selectAll")
    public Result<List> selectAll() throws Exception {
        List<Book> books = bookService.selectAll();
        return Result.success(books);
    }


}
