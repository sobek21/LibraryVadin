package com.example.application.views.helloworld;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "hello-world", layout = MainView.class)
@PageTitle("Hello World")
@CssImport(value = "./styles/views/helloworld/google.css" , id = "google")
@RouteAlias(value = "", layout = MainView.class)
public class HelloWorldView extends VerticalLayout {

    Grid<VolumeInfo> grid = new Grid<>(VolumeInfo.class);

    Label author = new Label("Author");
    Label name = new Label("Suggest Name");
    Label description = new Label("Description");
    Label year = new Label("Publish year");
    Label actors = new Label("Actors");
    TextField textField = new TextField("Szukaj ksiązki");
    Button button = new Button("Szukaj");
    H1 h1 = new H1("GOOGLE BOOKS SEARCH API");



    private BookSearchService bookSearchService;

    @Autowired
    public HelloWorldView(BookSearchService bookSearchService) {
        this.bookSearchService = bookSearchService;

        addClassName("google");

        h1.getElement().getStyle().set("font-size", "70px");

        button.addClickListener(buttonClickEvent -> refresh());


add(h1);
        add(author, description, name,year,actors, textField, button);



    }

    public void refresh() {
        author.setText(String.valueOf(bookSearchService.getBooks(textField.getValue()).getAuthors()));
        description.setText(bookSearchService.getBooks(textField.getValue()).getDescription());
        name.setText(bookSearchService.getBooks(textField.getValue()).getTitle());
        year.setText(bookSearchService.getBooks(textField.getValue()).getPublishedDate());
        actors.setText(String.valueOf(bookSearchService.getBooks(textField.getValue()).getCategories()));

    }
}
