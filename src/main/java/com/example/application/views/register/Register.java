package com.example.application.views.register;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("Register")
public class Register extends VerticalLayout {

    TextField firstname = new TextField("firstname");
    TextField lastname = new TextField("firstname");
    TextField username = new TextField("firstname");
    TextField email = new TextField("firstname");
    TextField password = new TextField("firstname");
    Button button = new Button("Register");

    public Register() {

setSizeFull();
setAlignItems(Alignment.CENTER);
        add(new H1("Register | Library"),firstname,lastname,username,email,password,button);
    }



    }

