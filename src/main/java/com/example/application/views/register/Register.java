package com.example.application.views.register;


import com.example.application.domain.User;
import com.example.application.service.EmailService;
import com.example.application.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Route("register")
@Component
@Push
@UIScope
public class Register extends VerticalLayout {

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    Binder<User> binder = new BeanValidationBinder<>(User.class);

    TextField firstName = new TextField("Fist Name");
    TextField lastName = new TextField("Last Name");
    HorizontalLayout firstLastName = new HorizontalLayout();
    TextField username = new TextField("Username");
    TextField email = new TextField("Email");
    HorizontalLayout userEmail = new HorizontalLayout();
    PasswordField password = new PasswordField("Password");

    Button register = new Button("Register");
    Label infoRegister = new Label();

    Anchor loginAnchor = new Anchor("/login",  "Already Registered User? Click here to login");
    H1 h1 = new H1("REGISTER | LIBRARY");

    User user = new User();

    public UserService userService;

    public EmailService emailService;

    public Register(UserService userService,EmailService emailService) {
        this.userService=userService;
        this.emailService=emailService;

       setAlignItems(Alignment.CENTER);

       firstLastName.add(firstName,lastName);
       userEmail.add(username,email);

        binder.bindInstanceFields(this);
        binder.forField(username).
                withValidator(v -> userService.findAll().stream().noneMatch(p -> p.getUsername().equals(v)), "Name, must be unique")
                .withValidator(v -> v.length() >=4 && v.length()<100, "Size min=4, max=100")
                .bind(User::getUsername, User::setUsername);

        binder.forField(email).
                withValidator(v -> userService.findAll().stream().noneMatch(p -> p.getEmail().equals(v)), "Email, must be unique")
                .withValidator(v -> v.length() >=4 && v.length()<100, "Size min=4, max=100")
                .bind(User::getEmail, User::setEmail);

        binder.setBean(user);

        add(h1,infoRegister,firstLastName,userEmail,password,register, loginAnchor);

        register.addClickListener(buttonClickEvent -> createUser(user));

    }
    public void createUser(User user) {

        user.setRole("USER");
        if (binder.isValid()) {

            userService.registerUser(user);
           // emailService.send(
                  //  new Mail(
                     //       email.getValue(),
                      //      "Register",
                       //     "Thank you for registering in our library"));
            infoRegister.setText("Registration Success");

            user.setUserID(null);

        } else {
            binder.validate();
            infoRegister.setText("Register Unvalid");
        }
    }
}

















