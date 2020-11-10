package com.example.application.views.empty;

import com.example.application.views.empty1.User;
import com.example.application.views.empty1.UserService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;



@Route(value = "empty", layout = MainView.class)
@PageTitle("BookList")
@CssImport("./styles/views/empty/empty-view.css")
public class BookView extends VerticalLayout {

Button button = new Button("Dodaj do ulubionych");
Button button1 = new Button("Wypożycz");
Label label = new Label();

    GridCrud<Book> crud = new GridCrud<>(Book.class);

    private Binder <Book>binder = new Binder<>(Book.class);

HorizontalLayout horizontalLayout = new HorizontalLayout();






private BookService bookService;
private UserService userService;

   @Autowired
    public BookView(BookService bookService,UserService userService) {
       this.bookService=bookService;
       this.userService=userService;


       configureGridCrud();

        horizontalLayout.add(button,button1,label);
        button.addClickListener(buttonClickEvent -> label.setText("Dodano do ulubionych"));

        button1.addClickListener(buttonClickEvent -> wypozycz());

        add(crud,horizontalLayout);
setSizeFull();



       crud.getGrid().asSingleSelect().addValueChangeListener(event -> binder.setBean(event.getValue()));



    }
    public void configureGridCrud() {


        crud.setAddOperation(bookService::add);
        crud.setFindAllOperation(bookService::findAll);
        crud.setDeleteOperation(bookService::delete);
        crud.setUpdateOperation(bookService::update);
        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getGrid().removeColumnByKey("user");
        if (false) {

            crud.getGrid().addColumn(user ->
            {
                User user1 = user.getUser();
                return user1 == null ? "-" : user1.getUsername();

            }).setHeader("User");
        }
    }


    public void wypozycz() {

       String name = (VaadinServletService.getCurrentServletRequest().getUserPrincipal().getName());

        User user = userService.findByUserName(name);

        Book book = binder.getBean();
        book.setUser(user);

        bookService.add(book);

        crud.refreshGrid();





    }

    public void setBook() {


    }

}
