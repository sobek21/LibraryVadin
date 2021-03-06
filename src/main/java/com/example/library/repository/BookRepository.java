package com.example.library.repository;

import com.example.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select c from Book c " +
            "where lower(c.title) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.author) like lower(concat('%', :searchTerm, '%'))")
        //
    List<Book> search(@Param("searchTerm") String searchTerm); //
}

