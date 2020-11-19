package com.example.application.views.helloworld;

import com.example.application.domain.Book;
import com.example.application.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private BookService bookService;

    @Autowired
    public RestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/getAll")
    public List<Book> findAll() {
        return bookService.findAll();
    }
}

