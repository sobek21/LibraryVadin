package com.example.application.dto;

import com.example.application.domain.BookStatus;

import java.time.LocalDate;

public class BookDto {

    private long bookID;

    private String title;

    private String author;

    private String bookType;

    private BookStatus bookStatus;

    private LocalDate localDate = LocalDate.now();

    private LocalDate localDate1;

    private UserDto user;

    public BookDto() {
    }

    public BookDto( String title, String author, String bookType, BookStatus bookStatus, LocalDate localDate, LocalDate localDate1, UserDto user) {

        this.title = title;
        this.author = author;
        this.bookType = bookType;
        this.bookStatus = bookStatus;
        this.localDate = localDate;
        this.localDate1 = localDate1;
        this.user = user;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getLocalDate1() {
        return localDate1;
    }

    public void setLocalDate1(LocalDate localDate1) {
        this.localDate1 = localDate1;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
