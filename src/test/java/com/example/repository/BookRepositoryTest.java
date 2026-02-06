package com.example.repository;

import com.example.entity.Author;
import com.example.entity.Book;
import com.example.entity.User;
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
public class BookRepositoryTest {

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    private UserRepository userRepository;

    private Author author;

    private User user;

    private Book book;

    @BeforeEach
    public void createBook() {
        String authorName = UUID.randomUUID().toString();
        String authorLastName = UUID.randomUUID().toString();
        author = new Author(authorName, authorLastName);
        authorRepository.save(author);

        String userName = UUID.randomUUID().toString();
        String userLastName = UUID.randomUUID().toString();
        String userPassword = UUID.randomUUID().toString();
        user = new User(userName, userLastName, userPassword);
        userRepository.save(user);

        String bookTitle = UUID.randomUUID().toString();
        book = new Book(bookTitle, author, user);
        bookRepository.save(book);
    }

    @Autowired
    public BookRepositoryTest(BookRepository bookRepository,
                              AuthorRepository authorRepository,
                              UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
    }

    @Test
    void findByTitleTest() {
        List<Book> foundBooks = bookRepository.findByTitle(book.getTitle());
        assertFoundBook(foundBooks);
    }

    @Test
    void findByAuthorNameOrLastNameTest() {
        List<Book> foundBooks = bookRepository.findByAuthorNameOrLastName(author.getName(), null);
        assertFoundBook(foundBooks);

        foundBooks = bookRepository.findByAuthorNameOrLastName(null, author.getLastName());
        assertFoundBook(foundBooks);

    }

    @Test
    void findByUserNameOrLastNameTest() {
        List<Book> foundBooks = bookRepository.findByUserNameOrLastName(user.getName(), null);
        assertFoundBook(foundBooks);

        foundBooks = bookRepository.findByUserNameOrLastName(null, user.getLastName());
        assertFoundBook(foundBooks);
    }

    private void assertFoundBook(List<Book> foundBooks) {
        Assertions.assertNotNull(foundBooks);
        Assertions.assertEquals(book.getId(), foundBooks.get(0).getId());
        Assertions.assertEquals(book.getTitle(), foundBooks.get(0).getTitle());
    }
}