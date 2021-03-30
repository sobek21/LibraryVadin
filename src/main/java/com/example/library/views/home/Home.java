package com.example.library.views.home;

import com.example.library.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "home", layout = MainView.class)
@PageTitle("Home")
@CssImport("./styles/views/helloworld1/hello-world1-view.css")
public class Home extends VerticalLayout {

    H1 h1 = new H1("LIBRARY");
    H1 h2 = new H1("");



    public Home() {
        setAlignSelf(Alignment.CENTER,h1);
        add(h1);

    }
}
