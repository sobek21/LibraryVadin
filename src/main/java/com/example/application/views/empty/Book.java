package com.example.application.views.empty;


import com.example.application.views.empty1.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
public class Book  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookID;


    @NotNull
    @Column(name = "NAME", unique = true)
    private String name;


    @NotNull
    private String author;

    private  BookType bookType;

    private BookStatus bookStatus;

    LocalDate localDate = LocalDate.now();

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

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

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

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

    public void setBookID(Long bookID) {
        this.bookID = bookID;
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


}
