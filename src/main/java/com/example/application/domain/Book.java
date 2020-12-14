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
    private String title;

    @NotNull
    private String author;

    private String bookType;

    private BookStatus bookStatus;

    private LocalDate created = LocalDate.now();

    private LocalDate deadline;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;


    public Book() {
    }

    public Book(@NotNull String title, @NotNull String author, String bookType, BookStatus bookStatus, LocalDate localDate, LocalDate localDate1, User user) {
        this.title = title;
        this.author = author;
        this.bookType = bookType;
        this.bookStatus = bookStatus;
        this.created = localDate;
        this.deadline = localDate1;
        this.user = user;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate localDate) {
        this.created = localDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate localDate1) {
        this.deadline = localDate1;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
