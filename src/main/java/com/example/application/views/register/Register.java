package com.example.application.views.register;


import com.example.application.domain.User;
import com.example.application.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Route("Register")
public class Register extends VerticalLayout {

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    Binder<User> binder = new BeanValidationBinder<>(User.class);

    TextField firstName = new TextField("Fist Name");
    TextField lastName = new TextField("Last Name");
    TextField username = new TextField("Username");
    TextField email = new TextField("Email");
    TextField password = new TextField("Password");

    Button register = new Button("Register");


    User user = new User();


    public Register() {


        binder.bindInstanceFields(this);
        binder.forField(username).
                withValidator(v -> userService.findAll().stream().noneMatch(p -> p.getUsername().equals(v)), "Name must be unique")
                .withValidator(a -> a.length() > 3, "test")
                .bind(User::getUsername, User::setUsername);
        binder.setBean(user);


        register.addClickListener(buttonClickEvent -> createUser(user));
        add(firstName, lastName, username, email, password, register);

    }

    public void createUser(User user) {
        this.user = user;
        user.setRole("USER");
        user.setPassword(bCryptPasswordEncoder().encode(password.getValue()));
        if (binder.isValid()) {
            userService.add(user);

        }
    }
}

















