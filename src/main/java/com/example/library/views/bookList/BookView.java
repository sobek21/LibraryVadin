package com.example.library.views.bookList;

import com.example.library.domain.Book;
import com.example.library.domain.BookStatus;
import com.example.library.domain.Mail;
import com.example.library.domain.User;
import com.example.library.service.BookService;
import com.example.library.service.EmailService;
import com.example.library.service.UserService;
import com.example.library.views.main.MainView;
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
import java.util.Optional;


@Route(value = "empty", layout = MainView.class)
@PageTitle("Book List")
@CssImport("./styles/views/empty/empty-view.css")
public class BookView extends VerticalLayout {


    Button button1 = new Button("Borrow the book");
    Label label = new Label();

    //private TextField addBookToUser = new TextField();
    //Button AddBookToUser = new Button("AddBookToUser");
    Button returnBook = new Button("ReturnBook");

    GridCrud<Book> crud = new GridCrud<>(Book.class);

    private Binder<Book> binder = new Binder<>(Book.class);

    HorizontalLayout horizontalLayout = new HorizontalLayout();
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();

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

        horizontalLayout.add( button1, label,returnBook);
       // horizontalLayout1.add(addBookToUser, AddBookToUser,returnBook);


        button1.addClickListener(buttonClickEvent -> wypozycz());

       // AddBookToUser.addClickListener(buttonClickEvent -> addBookToUser());

        returnBook.addClickListener(buttonClickEvent -> returnBook());


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
        crud.getGrid().setHeightByRows(true);


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

        Optional<User> user = userService.findByUserName(name);

        Book book = binder.getBean();

        if(book.getBookStatus() == BookStatus.Dostępna) {

            book.setDeadline(LocalDate.now().plusDays(14));
            book.setUser(user.get());
            book.setBookStatus(BookStatus.Wypożyczona);

            bookService.update(book);

            emailService.send(new Mail(
                    userService.getUser().getEmail(),
                    "Borrowed book",
                    "You borrowed a book : "+book.getTitle()+
                            "\n"+
                    "Deadline: "+book.getDeadline()

            ));


            crud.refreshGrid();
        }else {
            label.setText("Niedostepna"+book.getTitle()+userService.getRole());
        }


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
   // public void addBookToUser () {
     //   Book book = binder.getBean();

    //  Optional<User> userName = (userService.findByUserName(addBookToUser.getValue()));

     // book.setUser(userName.get());
      //  bookService.update(book);

      //  crud.refreshGrid();
  //  }
    public void returnBook() {
        Book book = binder.getBean();
        book.setUser(null);
        book.setBookStatus(BookStatus.Dostępna);
        bookService.update(book);
        crud.refreshGrid();
    }


}
