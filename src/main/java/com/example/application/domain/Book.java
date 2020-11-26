package com.example.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long bookID;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    private String author;

    private BookType bookType;

    private BookStatus bookStatus;

    private LocalDate localDate = LocalDate.now();

    private LocalDate localDate1;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Book() {
    }

    public Book(Long bookID, @NotNull String name, @NotNull String author, BookType bookType, BookStatus bookStatus, User user) {
        this.bookID = bookID;
        this.name = name;
        this.author = author;
        this.bookType = bookType;
        this.bookStatus = bookStatus;
        this.user = user;
    }


    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Long getBookID() {
        return bookID;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public LocalDate getLocalDate1() {
        return localDate1;
    }

    public void setLocalDate1(LocalDate localDate1) {
        this.localDate1 = localDate1;
    }
}
