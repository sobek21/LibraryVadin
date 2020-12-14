package com.example.application.service;

import com.example.application.domain.Book;
import com.example.application.mapper.BookMapper;
import com.example.application.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.security.Principal;
import java.util.List;

@Service
public class BookService implements CrudListener<Book> {

    private BookRepository bookRepository;
    private UserService userService;
    private BookMapper bookMapper;


    @Autowired
    public BookService(BookRepository bookRepository, UserService userService,BookMapper bookMapper) {
        this.userService = userService;

        this.bookRepository = bookRepository;
        this.bookMapper =bookMapper;
    }

    @Override
    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    @Override
    public Book add(Book book) {


        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override

    public void delete(Book book) {


        bookRepository.delete(book);
    }

    private void filter() {

    }

    public void addFavorites(Book book, Principal principal) {

    }

    public List<Book> findAllFilter(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return bookRepository.findAll();
        } else {
            return bookRepository.search(stringFilter);
        }
    }
}
