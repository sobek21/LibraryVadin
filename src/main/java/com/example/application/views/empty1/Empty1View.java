package com.example.application.views.empty1;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "empty1", layout = MainView.class)
@PageTitle("UserList")
@CssImport("./styles/views/empty1/empty1-view.css")
public class Empty1View extends VerticalLayout {

    public Empty1View(UserService userService) {
        GridCrud<User> crud = new GridCrud<>(User.class);
        crud.setAddOperation(userService::add);
        crud.setFindAllOperation(userService::findAll);
        crud.setDeleteOperation(userService::delete);
        crud.setUpdateOperation(userService::update);
        crud.getCrudFormFactory().setUseBeanValidation(true);


        add(crud);
        setSizeFull();
    }

}
