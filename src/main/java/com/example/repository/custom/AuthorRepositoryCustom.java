package com.example.repository.custom;

import com.example.entity.Author;

import java.util.List;

public interface AuthorRepositoryCustom {
    List<Author> findByNameOrLastName(String name, String lastName);
}
