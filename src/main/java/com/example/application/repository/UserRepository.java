package com.example.application.repository;

import com.example.application.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {

    User findByFirstName(String firstname);

    User findByUsername(String name);

    @Query("select c from User c " +
            "where lower(c.email) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.username) like lower(concat('%', :searchTerm, '%'))")
        //
    List<User> search(@Param("searchTerm") String searchTerm); //
}

