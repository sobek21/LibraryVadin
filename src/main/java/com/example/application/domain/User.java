package com.example.application.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity

@Table(name = "USERS",uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;
    @Column(name = "username")
    @Size(min = 4, max = 100)
    private String username;

    @NotEmpty
    @Email
    private String email;
    @Size(min = 4, max = 100)
    private String password;

    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Book> books;

    public User() {
    }

    public User(String firstName, String lastName, String username, String email, String password, String role, List<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.books = books;
    }

    public Long getUserID() {
        return userID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }




}
