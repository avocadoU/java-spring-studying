package com.example.repository.custom;

import com.example.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepositoryCustom {

    List<Book> findByTitle(String title);

    List<Book> findByAuthorNameOrLastName(String name, String lastName);

    List<Book> findByUserNameOrLastName(String name, String lastName);
}
