package com.example.application.views.bookMenu;

import com.example.application.domain.Book;
import com.example.application.domain.BookStatus;
import com.example.application.domain.Mail;
import com.example.application.domain.User;
import com.example.application.service.BookService;
import com.example.application.service.EmailService;
import com.example.application.service.UserService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.time.LocalDate;


@Route(value = "empty", layout = MainView.class)
@PageTitle("BookList")
@CssImport("./styles/views/empty/empty-view.css")
public class BookView extends VerticalLayout {

    Button button = new Button("Dodaj do ulubionych");
    Button button1 = new Button("Wypożycz");
    Label label = new Label();

    GridCrud<Book> crud = new GridCrud<>(Book.class);

    private Binder<Book> binder = new Binder<>(Book.class);

    HorizontalLayout horizontalLayout = new HorizontalLayout();

    private TextField filterText = new TextField();


    private BookService bookService;
    private UserService userService;
    private EmailService emailService;


    @Autowired
    public BookView(BookService bookService, UserService userService,EmailService emailService) {
        this.bookService = bookService;
        this.userService = userService;
        this.emailService=emailService;



        configureGridCrud();

        horizontalLayout.add(button, button1, label);
        button.addClickListener(buttonClickEvent -> label.setText("Dodano do ulubionych"));

        button1.addClickListener(buttonClickEvent -> wypozycz());


        configureFilter();

        add(filterText, crud,label, horizontalLayout);
        setSizeFull();


        crud.getGrid().asSingleSelect().addValueChangeListener(event -> binder.setBean(event.getValue()));


    }

    public void configureGridCrud() {


        crud.setAddOperation(bookService::add);
       // crud.setAddOperationVisible(userService.getRole().equals("ADMIN"));
        crud.setFindAllOperation(bookService::findAll);
        crud.setDeleteOperation(bookService::delete);
       // crud.setDeleteOperationVisible(userService.getRole().equals("ADMIN"));
        crud.setUpdateOperation(bookService::update);
       // crud.setUpdateOperationVisible(userService.getRole().equals("ADMIN"));

        crud.getGrid().getColumnByKey("bookID").setVisible(false);


        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getGrid().removeColumnByKey("user");
        if (true) {

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

        if(book.getBookStatus() == BookStatus.Dostępna) {

            book.setLocalDate1(LocalDate.now().plusDays(14));
            book.setUser(user);
            book.setBookStatus(BookStatus.Wypożyczona);

            bookService.add(book);

            emailService.send(new Mail(
                    userService.getUser().getEmail(),"Tak","Book: "+book.getName()+
                    "Data oddania: "+book.getLocalDate1()

            ));


            crud.refreshGrid();
        }else {
            label.setText("Niedostepna"+book.getName()+userService.getRole());
        }


    }

    public void dodajUlubionych() {


    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by name or author...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }


    private void updateList() {
        crud.getGrid().setItems(bookService.findAllFilter(filterText.getValue()));

    }


}
