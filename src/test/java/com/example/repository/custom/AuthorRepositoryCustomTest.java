package com.example.repository.custom;

import com.example.entity.Author;
import com.example.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
public class AuthorRepositoryCustomTest {

    private AuthorRepository authorRepository;

    private AuthorRepositoryCustom authorRepositoryCustom;

    @Autowired
    public AuthorRepositoryCustomTest(AuthorRepository authorRepository, AuthorRepositoryCustom authorRepositoryCustom) {
        this.authorRepository = authorRepository;
        this.authorRepositoryCustom = authorRepositoryCustom;
    }

    Author createAuthor() {
        String authorName = UUID.randomUUID().toString();
        String authorLastName = UUID.randomUUID().toString();
        Author author = new Author(authorName, authorLastName);

        authorRepository.save(author);
        return author;
    }

    @Test
    void findByNameOrLastNameTest() {
        Author author1 = createAuthor();
        Author author2 = createAuthor();

        List<Author> foundAuthors = authorRepositoryCustom.findByNameOrLastName(author1.getName(), null);
        assertFoundAuthor(foundAuthors, author1);

        foundAuthors = authorRepositoryCustom.findByNameOrLastName(null, author1.getLastName());
        assertFoundAuthor(foundAuthors, author1);

        foundAuthors = authorRepositoryCustom.findByNameOrLastName(author2.getName(), null);
        assertFoundAuthor(foundAuthors, author2);

        foundAuthors = authorRepositoryCustom.findByNameOrLastName(null, author2.getLastName());
        assertFoundAuthor(foundAuthors, author2);
    }

    private void assertFoundAuthor(List<Author> foundAuthors, Author author) {
        Assertions.assertNotNull(foundAuthors);
        Assertions.assertEquals(1, foundAuthors.size());
        Assertions.assertEquals(author.getId(), foundAuthors.get(0).getId());
    }
}
