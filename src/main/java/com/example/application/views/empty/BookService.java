package com.example.application.views.empty;

import com.example.application.views.empty1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.security.Principal;
import java.util.List;

@Service
public class BookService implements CrudListener<Book> {

    private BookRepository bookRepository;
    private UserService userService;

@Autowired
    public BookService(BookRepository bookRepository,UserService userService) {
    this.userService=userService;

    this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> findAll () {

        return bookRepository.findAll();
    }
    @Override
    public Book add ( Book book) {
        return bookRepository.save(book);
    }
    @Override
    public Book update( Book book) {
        return bookRepository.save(book);
    }
    @Override
    public void delete ( Book book) {
        bookRepository.delete(book);
    }
    private void filter() {

    }
    public void addFavorites(Book book, Principal principal) {

    }
    public List<Book> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return bookRepository.findAll();
        } else {
            return bookRepository.search(stringFilter);
        }
    }
}
