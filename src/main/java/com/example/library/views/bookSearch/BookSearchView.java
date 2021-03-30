package com.example.library.views.bookSearch;

import com.example.library.domain.bookSearch.VolumeInfo;
import com.example.library.service.BookSearchService;
import com.example.library.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "BookSearch", layout = MainView.class)
@PageTitle("Book Search")
@CssImport(value = "./styles/views/helloworld/google.css")
@RouteAlias(value = "", layout = MainView.class)
public class BookSearchView extends VerticalLayout {

    Grid<VolumeInfo> grid = new Grid<>(VolumeInfo.class);

    Label name = new Label();
    Label description = new Label();
    TextField textField = new TextField();
    Button button = new Button("Search");

    H1 h1 = new H1("SEARCH BOOKS");

    Grid<VolumeInfo> bookGrid = new Grid<>(VolumeInfo.class);

    VerticalLayout verticalLayout = new VerticalLayout();
    HorizontalLayout horizontalLayout = new HorizontalLayout();

    private BookSearchService bookSearchService;

    @Autowired
    public BookSearchView(BookSearchService bookSearchService) {
        this.bookSearchService = bookSearchService;
        addClassName("user-view");
        bookGrid.setColumns("authors", "publisher", "publishedDate", "categories", "pageCount");
        bookGrid.setHeightByRows(true);
        bookGrid.setVisible(false);
        verticalLayout.add(bookGrid);
        verticalLayout.setSizeFull();

        horizontalLayout.add(textField, button);

        setAlignSelf(Alignment.CENTER, horizontalLayout);
        setAlignSelf(Alignment.CENTER, h1);

        button.addClickListener(buttonClickEvent -> refresh());

        add(h1, horizontalLayout, name, verticalLayout);
        add(description);

    }

    public void refresh() {
        if (textField.getValue() == null || textField.getValue() == "") {
            bookGrid.setVisible(false);
        } else {
            bookGrid.setVisible(true);
            bookGrid.setItems(bookSearchService.getBooks(textField.getValue()));
            description.setText("Description: " +
                    String.valueOf(bookSearchService.getBooks(textField.getValue()).getDescription()));
            name.setText("Did you mean? : "+bookSearchService.getBooks(textField.getValue()).getTitle());

        }
    }
}
