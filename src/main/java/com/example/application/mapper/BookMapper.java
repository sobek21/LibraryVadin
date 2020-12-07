package com.example.application.mapper;

import com.example.application.domain.Book;
import com.example.application.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    @Autowired
    private UserMapper userMapper;


    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getBookType(),
                bookDto.getBookStatus(),
                bookDto.getLocalDate(),
                bookDto.getLocalDate1(),
               userMapper.mapToUser(bookDto.getUser())
        );
    }
    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getBookType(),
                book.getBookStatus(),
                book.getLocalDate(),
                book.getLocalDate1(),
                userMapper.mapToUserDto(book.getUser())
        );
    }
    public List<Book> mapToBookList(final List<BookDto> booklist) {
            return booklist.stream()
                    .map(this::mapToBook)
                    .collect(Collectors.toList());
    }
    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }

}
