package com.example.library.scheduled;

import com.example.library.domain.Book;
import com.example.library.domain.Mail;
import com.example.library.service.BookService;
import com.example.library.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduledEmai {

    @Autowired
    private BookService bookService;

    @Autowired
    private EmailService emailService;


    @Scheduled(cron = "0 0 12 * * *")

    public void checkDeadlineBooks() {
   List<Book> bookList = bookService.findAll().stream()
           .filter(a -> a.getDeadline().equals(LocalDate.now().minusDays(1)))
            .collect(Collectors.toList());

    System.out.println(bookList);

    for (Book a: bookList) {
        emailService.send(
                new Mail(
                        a.getUser().getEmail(),
                       "Reminder",
                        "The deadline for handing over the book is approaching :"
                        +a.getTitle()
                                +"\n"+ "Deadline:"+a.getDeadline())
        );

    }

}
}
