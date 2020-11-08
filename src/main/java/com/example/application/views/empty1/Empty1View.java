package com.example.application.views.empty1;

import com.example.application.views.empty.Book;
import com.example.application.views.empty.BookService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.util.Set;

@Route(value = "empty1", layout = MainView.class)
@PageTitle("UserList")
@CssImport("./styles/views/empty1/empty1-view.css")
public class Empty1View extends VerticalLayout {

    GridCrud<User> crud = new GridCrud<>(User.class);

    private UserService userService;

    private BookService bookService;

    public Empty1View(UserService userService,BookService bookService) {

        this.bookService=bookService;
        this.userService=userService;


conigureGridCrud();

        add(crud);
        setSizeFull();
    }
    public void conigureGridCrud() {
        crud.setAddOperation(userService::add);
        crud.setFindAllOperation(userService::findAll);
        crud.setDeleteOperation(userService::delete);
        crud.setUpdateOperation(userService::update);
        crud.getCrudFormFactory().setUseBeanValidation(true);

        crud.getGrid().removeColumnByKey("books");

        crud.getGrid().addColumn(book -> {

                Set<Book> books = bookService.f
                return book1 == null ? "-" :

    }

}
