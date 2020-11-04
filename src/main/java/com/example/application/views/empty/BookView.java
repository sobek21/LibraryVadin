package com.example.application.views.empty;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;


@Route(value = "empty", layout = MainView.class)
@PageTitle("BookList")
@CssImport("./styles/views/empty/empty-view.css")
public class BookView extends VerticalLayout {

Button button = new Button("Dodaj do ulubionych");
Button button1 = new Button("Wypożycz");
Label label = new Label();

HorizontalLayout horizontalLayout = new HorizontalLayout();


    public BookView(BookService bookService) {
        GridCrud<Book> crud = new GridCrud<>(Book.class);
        crud.setAddOperation(bookService::add);
        crud.setFindAllOperation(bookService::findAll);
        crud.setDeleteOperation(bookService::delete);
        crud.setUpdateOperation(bookService::update);
        crud.getCrudFormFactory().setUseBeanValidation(true);

        horizontalLayout.add(button,button1,label);
        button.addClickListener(buttonClickEvent -> label.setText("Dodano do ulubionych"));


        add(crud,horizontalLayout);
setSizeFull();
    }

}
