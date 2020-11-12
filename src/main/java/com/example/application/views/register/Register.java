package com.example.application.views.register;


import com.example.application.views.empty1.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("Register")
public class Register extends VerticalLayout {

    Binder<User> binder = new BeanValidationBinder<>(User.class);

    TextField firstname = new TextField("Fist Name");
    TextField lastname = new TextField("Last Name");
    TextField username = new TextField("Username");
    TextField email = new TextField("Email");
    TextField password = new TextField("Password");
    Button button = new Button("Register");

    public Register() {

        binder.bindInstanceFields(this);

setSizeFull();
setAlignItems(Alignment.CENTER);
        add(new H1("Register | Library"),firstname,lastname,username,email,password,button);
       button.addClickListener(buttonClickEvent -> System.out.println(binder.getBean()));
    }




}

