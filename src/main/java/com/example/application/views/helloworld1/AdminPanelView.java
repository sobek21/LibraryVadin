package com.example.application.views.helloworld1;

import com.example.application.service.EmailService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "hello-world1", layout = MainView.class)
@PageTitle("Hello World1")
@CssImport("./styles/views/helloworld1/hello-world1-view.css")
public class AdminPanelView extends HorizontalLayout {

    public EmailService emailService;

    public AdminPanelView(EmailService emailService) {
        this.emailService=emailService;
    }


}
