package com.example.library.views.account;

import com.example.library.domain.User;
import com.example.library.service.UserService;
import com.example.library.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "account", layout = MainView.class)
@PageTitle("My Account")
@CssImport("./styles/views/empty1/empty1-view.css")
public class MyAccount extends VerticalLayout {

    Label firstNameL = new Label("First Name");
    TextField firstNameT = new TextField();
    Label lastNameL = new Label("Last Name");
    TextField lastNameT = new TextField();
    Label usernameL = new Label("Username");
    TextField usernameT = new TextField();
    Label emailL = new Label("Email");
    TextField emailT = new TextField();
    Label passwordL = new Label("Password");
    PasswordField passwordT = new PasswordField();

    Button edit = new Button("Edit");





  private UserService userService;



    public MyAccount(UserService userService) {
        this.userService=userService;

        User user = new User();
        user = userService.getUser();


        firstNameT.setValue(user.getFirstName());
        lastNameT.setValue(user.getLastName());
        usernameT.setValue(user.getUsername());
        emailT.setValue(user.getEmail());
        passwordT.setValue(user.getPassword());


        add(firstNameL,firstNameT,lastNameL,lastNameT,usernameL,usernameT,
                emailL,emailT,passwordL,passwordT,edit
        );
        edit.addClickListener(buttonClickEvent -> edit());

    }
    private void edit() {
        User user = new User();
        user = userService.getUser();

        user.setFirstName(firstNameT.getValue());
        user.setLastName(lastNameT.getValue());
        user.setPassword(passwordT.getValue());
        user.setEmail(emailT.getValue());
        user.setUsername(usernameT.getValue());

        userService.update(user);

    }
}
