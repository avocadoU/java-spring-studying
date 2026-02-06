package com.example.repository;

import com.example.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.author.name = :name OR b.author.lastName = :lastName")
    List<Book> findByAuthorNameOrLastName(
            @Param("name") String name,
            @Param("lastName") String lastName
    );

    @Query("SELECT b FROM Book b WHERE b.user.name = :name OR b.user.lastName = :lastName")
    List<Book> findByUserNameOrLastName(
            @Param("name") String name,
            @Param("lastName") String lastName
    );
}
