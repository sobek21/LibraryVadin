package com.example.application.views.helloworld1;


import com.example.application.views.empty.Book;
import com.example.application.views.empty.BookService;
import com.example.application.views.empty1.User;
import com.example.application.views.empty1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class RestApi {

   // @Autowired
  //  Principal principal;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    public Book addToFav() {
        User user = userService.findByUserName(principal.getName());

        return null;
    }


}
