package com.example.repository;

import com.example.entity.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
public class AuthorRepositoryTest {

    private AuthorRepository authorRepository;

    private Author author;

    @Autowired
    public AuthorRepositoryTest(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @BeforeEach
    void createAuthor() {
        String authorName = UUID.randomUUID().toString();
        String authorLastName = UUID.randomUUID().toString();
        author = new Author(authorName, authorLastName);
        authorRepository.save(author);
    }

    @Test
    void findByNameOrLastNameTest() {
        List<Author> foundAuthors = authorRepository.findByNameOrLastName(author.getName(), null);
        assertFoundAuthor(foundAuthors);

        foundAuthors = authorRepository.findByNameOrLastName(null, author.getLastName());
        assertFoundAuthor(foundAuthors);
    }

    private void assertFoundAuthor(List<Author> foundAuthors) {
        Assertions.assertNotNull(foundAuthors);
        Assertions.assertEquals(author.getId(), foundAuthors.get(0).getId());
    }
}
