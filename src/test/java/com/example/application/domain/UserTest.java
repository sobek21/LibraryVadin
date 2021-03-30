package com.example.application.domain;


import com.example.library.Application;
import com.example.library.domain.Book;
import com.example.library.domain.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Transactional
public class UserTest {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private BookRepository bookDao;

    @Test
    public void saveUserTest() {

        //given
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword("Hasło");
        user.setEmail("test@o2.pl");
        user.setUsername("Michał");

        //when
        userDao.save(user);
        long id = user.getUserID();
        Optional<User> optionalUser = userDao.findById(id);

        //then
        Assert.assertTrue(optionalUser.isPresent());

        //clean up
        userDao.deleteById(id);
    }

    @Test
    public void deleteUserTest() {

        //given
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword("Hasło!@123Asas");
        user.setEmail("test@o2.pl");
        user.setUsername("Michał");

        //when
        userDao.save(user);
        long id = user.getUserID();
        boolean wasSaved = userDao.existsById(id);

        userDao.deleteById(id);
        boolean isExist = userDao.existsById(id);

        //then
        Assert.assertTrue(wasSaved);
        Assert.assertFalse(isExist);

        //clean up
    }

    @Test
    public void updateUserTest() {

        //given
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword("Hasło");
        user.setEmail("test@o2.pl");
        user.setUsername("Mateusz");
        userDao.save(user);
        long userId = user.getUserID();
        String oldPassword = user.getPassword();

        //when
        user.setPassword("New Password");
        userDao.save(user);

        //then
        Assert.assertNotEquals(oldPassword, user.getPassword());
        Assert.assertEquals("New Password", user.getPassword());

        //clean up
        userDao.delete(user);
    }
    @Test
    public void readUserTest() {

        //given
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword("Hasło");
        user.setEmail("test@o2.pl");
        user.setUsername("Mateusz");
        userDao.save(user);
        long userId = user.getUserID();

        //when
        User readUser = userDao.findById(userId).get();

        //then
        Assert.assertEquals(readUser.getPassword(), "Hasło");
        Assert.assertEquals(readUser.getUsername(), "Mateusz");

        //clean up
        userDao.delete(user);
    }
    @Test
    public void relationWithBook() {

        //Given
        Book book = new Book();
        book.setAuthor("Rowling");
        book.setTitle("HarryPotter");

        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword("Hasło!@123Asas");
        user.setEmail("test@o2.pl");
        user.setUsername("Mateusz");

        user.setBooks(Collections.singletonList(book));
        bookDao.save(book);

        userDao.save(user);
        long orderId = book.getBookID();


        userDao.delete(user);
        boolean bookIsPresentAfterRemoveUser = bookDao.existsById(orderId);

        //then

        Assert.assertTrue(bookIsPresentAfterRemoveUser);


    }


}
