package com.example.library.views.userList;

import com.example.library.domain.User;
import com.example.library.service.BookService;
import com.example.library.service.UserService;
import com.example.library.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "empty1", layout = MainView.class)
@PageTitle("User List")
@CssImport("./styles/views/empty1/empty1-view.css")
public class UserView extends VerticalLayout {

    private TextField filterText = new TextField();

    GridCrud<User> crud = new GridCrud<>(User.class);

    private UserService userService;

    private BookService bookService;



    @Autowired
    public UserView(UserService userService, BookService bookService) {
        this.bookService = bookService;
        this.userService = userService;


        conigureGridCrud();
        configureFilter();

        add(filterText, crud);
        setSizeFull();
    }

    public void conigureGridCrud() {
        crud.setAddOperation(userService::add);
        crud.setFindAllOperation(userService::findAll);
        crud.setDeleteOperation(userService::delete);
        crud.setUpdateOperation(userService::update);
        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getGrid().setHeightByRows(true);

        crud.getGrid().removeColumnByKey("books");
        crud.getGrid().removeColumnByKey("userID");


    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by email or username...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }


    private void updateList() {
        crud.getGrid().setItems(userService.findAllFilter(filterText.getValue()));

    }

}
