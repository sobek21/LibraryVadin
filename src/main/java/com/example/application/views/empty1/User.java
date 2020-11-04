package com.example.application.views.empty1;

import com.example.application.views.empty.Book;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;

    @OneToMany(mappedBy="user",fetch = FetchType.EAGER )
    private Set<Book> books;

    public User() {
    }

    public User(Long userID, String firstName, String lastName, String userName, String email, String password, Set<Book> books) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.books = books;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
