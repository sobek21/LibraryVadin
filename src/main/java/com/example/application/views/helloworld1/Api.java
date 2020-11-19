package com.example.application.views.helloworld1;

import com.example.application.domain.Book;
import com.example.application.domain.User;
import com.example.application.service.BookService;
import com.example.application.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
public class Api {

    private UserService userService;

    private BookService bookService;

    @Autowired
    public Api(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @JsonIgnore
    @JsonIgnoreProperties
    @GetMapping("/api/books")
    public Set<Book> findAll (Principal principal) {

       User user1 = userService.findByUserName(principal.getName());
        return user1.getBooks();

    }
}
