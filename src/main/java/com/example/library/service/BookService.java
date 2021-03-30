package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.List;

@Service
public class BookService implements CrudListener<Book> {

    private BookRepository bookRepository;
    private UserService userService;

    @Autowired
    public BookService(BookRepository bookRepository, UserService userService) {
        this.userService = userService;
        this.bookRepository = bookRepository;

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
    public List<Book> findAllFilter(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return bookRepository.findAll();
        } else {
            return bookRepository.search(stringFilter);
        }
    }
}
