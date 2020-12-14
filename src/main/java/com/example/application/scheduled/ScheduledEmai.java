package com.example.application.scheduled;

import com.example.application.domain.Book;
import com.example.application.domain.Mail;
import com.example.application.service.BookService;
import com.example.application.service.EmailService;
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


@Scheduled(cron = "0 0 10 * * *")
    public void test() {
    List<Book> bookList = bookService.findAll().stream()
            .filter(a -> a.getDeadline().equals(LocalDate.now().minusDays(1)))
            .collect(Collectors.toList());

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
