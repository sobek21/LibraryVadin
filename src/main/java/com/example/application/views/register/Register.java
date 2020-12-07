package com.example.application.views.register;


import com.example.application.domain.Mail;
import com.example.application.domain.User;
import com.example.application.service.EmailService;
import com.example.application.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
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


    private UserService userService;

    private EmailService emailService;

    Binder<User> binder = new BeanValidationBinder<>(User.class);

    TextField firstName = new TextField("Fist Name");
    TextField lastName = new TextField("Last Name");
    TextField username = new TextField("Username");
    TextField email = new TextField("Email");
    PasswordField password = new PasswordField("Password");

    Button register = new Button("Register");

    Label label = new Label();



    Anchor anchor = new Anchor("/login", "Doing it right with...");
    H1 h1 = new H1("REGISTER | LIBRARY");

    User user = new User();



    public Register(UserService userService) {
        this.userService=userService;

       setAlignItems(Alignment.CENTER);


        binder.bindInstanceFields(this);
        binder.forField(username).
                withValidator(v -> userService.findAll().stream().noneMatch(p -> p.getUsername().equals(v)), "Name must be unique")
                .withValidator(v -> v.length() >=4 && v.length()<100, "must be")
                .bind(User::getUsername, User::setUsername);


        binder.setBean(user);


        add(h1,label,firstName,lastName,email,username,password,register,anchor);


        register.addClickListener(buttonClickEvent -> createUser(user));

    }

    public void createUser(User user) {
        this.user = user;

        user.setRole("USER");
        if (binder.isValid()) {
            userService.add(user);
            emailService.send(
                    new Mail(
                            email.getValue(),
                            "Register",
                            "Thank you for registering in our library"));
            label.setText("Register Succesfull");
        } else {
            binder.validate();
            label.setText("Register Unvalid");

        }
    }
}

















