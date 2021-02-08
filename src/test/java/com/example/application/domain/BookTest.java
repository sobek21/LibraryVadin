package com.example.application.domain;

import com.example.library.Application;
import com.example.library.domain.Book;
import com.example.library.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Transactional

public class BookTest {

    @Autowired
   public BookRepository bookDao;

    @Test
    public void saveTest() {

        //Given
        Book book = new Book();
        book.setTitle("Harry");
        book.setAuthor("Test");

        //When
        bookDao.save(book);
        long idBook = book.getBookID();
        boolean bookIsPresent = bookDao.existsById(idBook);

        //Then
        Assert.assertTrue(bookIsPresent);

        //clean
        bookDao.delete(book);

    }
    @Test
    public void readBook() {

        Book book = new Book();
        book.setTitle("Harry");
        book.setAuthor("Test");

        //When
        bookDao.save(book);
        long bookId = book.getBookID();
        Book book1 = bookDao.findById(bookId).get();

        Assert.assertEquals(book1.getTitle(), "Harry");
        Assert.assertEquals(book1.getAuthor(), "Test");

    }
    @Test
    public void deleteBook() {

        //Given
        Book book = new Book();
        book.setTitle("Harry");
        book.setAuthor("Test");

        //When
        bookDao.save(book);
        long idBook = book.getBookID();
       boolean bookSave = bookDao.existsById(idBook);
        bookDao.delete(book);
        boolean afterDelete = bookDao.existsById(idBook);

        //Then
        Assert.assertTrue(bookSave);
        Assert.assertFalse(afterDelete);


    }

}
