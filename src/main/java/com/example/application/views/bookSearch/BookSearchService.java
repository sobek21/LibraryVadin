package com.example.application.views.bookSearch;

import com.example.application.domain.bookSearch.BookFact;
import com.example.application.domain.bookSearch.VolumeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Service
public class BookSearchService {



    private RestTemplate restTemplate;

    @NotNull
    @NotEmpty
    private String bookTitle = "";

    private String url = "https://www.googleapis.com/books/v1/volumes?q=" + bookTitle + "&key=AIzaSyDQ5S3dnCdR3YKAviYoyqsQFCekLK-1cZs&startIndex=1&maxResults=1";


    public BookSearchService() {
        super();
    }


    @Autowired
    public BookSearchService(RestTemplate restTemplate) {
        super();
        this.restTemplate = restTemplate;

    }


    public VolumeInfo getBooks(String bookTitle) {
        this.bookTitle = bookTitle;


        ResponseEntity<BookFact> bookFact = restTemplate.exchange(
                "https://www.googleapis.com/books/v1/volumes?q=" + bookTitle + "&startIndex=1&maxResults=1",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                BookFact.class);


        VolumeInfo volumeInfo = bookFact.getBody().getItems().stream().findFirst().get().getVolumeInfo();

        System.out.println(volumeInfo);


        return volumeInfo;

    }

}
