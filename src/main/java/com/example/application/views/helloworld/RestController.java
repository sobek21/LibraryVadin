package com.example.application.views.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private BookSearchService bookSearchService;

    @Autowired
    public RestController(BookSearchService bookSearchService) {
        this.bookSearchService = bookSearchService;
    }
    @GetMapping("api/book")
    public void test() {


    }
}
