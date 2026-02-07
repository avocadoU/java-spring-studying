package com.example.repository.custom;

import com.example.entity.Author;
import com.example.entity.Book;
import com.example.entity.User;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
public class BookRepositoryCustomTest {

    private BookRepository bookRepository;

    private BookRepositoryCustom bookRepositoryCustom;

    private UserRepository userRepository;

    private AuthorRepository authorRepository;

    @Autowired
    public BookRepositoryCustomTest(BookRepository bookRepository,
                                    BookRepositoryCustom bookRepositoryCustom,
                                    UserRepository userRepository,
                                    AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookRepositoryCustom = bookRepositoryCustom;
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
    }

    List<Book> createBooks() {
        List<Book> books = new ArrayList<>();

        Author author1 = createAuthor();
        Author author2 = createAuthor();

        User user1 = createUser();
        User user2 = createUser();

        String title1 = UUID.randomUUID().toString();
        String title2 = UUID.randomUUID().toString();
        String title3 = UUID.randomUUID().toString();
        String title4 = UUID.randomUUID().toString();

        Book book1 = new Book(title1, author1, user1);
        Book book2 = new Book(title2, author1, user2);
        Book book3 = new Book(title3, author2, user1);
        Book book4 = new Book(title4, author2, user2);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        bookRepository.saveAll(books);
        return books;
    }

    private User createUser() {
        String userName = UUID.randomUUID().toString();
        String userLastName = UUID.randomUUID().toString();
        String userPassword = UUID.randomUUID().toString();
        User user = new User(userName, userLastName, userPassword);

        userRepository.save(user);
        return user;
    }

    private Author createAuthor() {
        String authorName = UUID.randomUUID().toString();
        String authorLastName = UUID.randomUUID().toString();
        Author author = new Author(authorName, authorLastName);

        authorRepository.save(author);
        return author;
    }

    @Test
    void findByTitle() {
        List<Book> books = createBooks();
        Book expectedBook = books.get(0);

        Book foundBook = bookRepositoryCustom.findByTitle(expectedBook.getTitle()).get(0);

        Assertions.assertNotNull(foundBook);
        Assertions.assertEquals(expectedBook.getId(), foundBook.getId());
        Assertions.assertEquals(expectedBook.getTitle(), foundBook.getTitle());
    }

    @Test
    void findByAuthorNameOrLastName() {
        List<Book> books = createBooks();
        List<Book> foundBooks;
        Author expectedAuthor;

        for (Book book : books) {
            expectedAuthor = book.getAuthor();

            foundBooks = bookRepositoryCustom.findByAuthorNameOrLastName(expectedAuthor.getName(), null);
            assertFoundBooksByAuthor(foundBooks, expectedAuthor);

            foundBooks = bookRepositoryCustom.findByAuthorNameOrLastName(null, expectedAuthor.getLastName());
            assertFoundBooksByAuthor(foundBooks, expectedAuthor);
        }
    }

    private void assertFoundBooksByAuthor(List<Book> foundBooks, Author expectedAuthor) {
        Assertions.assertNotNull(foundBooks);
        Assertions.assertEquals(2, foundBooks.size());
        Assertions.assertEquals(foundBooks.get(0).getAuthor(), expectedAuthor);
        Assertions.assertEquals(foundBooks.get(1).getAuthor(), expectedAuthor);
    }

    @Test
    void findByUserNameOrLastName() {
        List<Book> books = createBooks();
        List<Book> foundBooks;
        User expectedUser;

        for (Book book : books) {
            expectedUser = book.getUser();

            foundBooks = bookRepositoryCustom.findByUserNameOrLastName(expectedUser.getName(), null);
            assertFoundBooksByUser(foundBooks, expectedUser);

            foundBooks = bookRepositoryCustom.findByUserNameOrLastName(null, expectedUser.getLastName());
            assertFoundBooksByUser(foundBooks, expectedUser);
        }
    }

    private void assertFoundBooksByUser(List<Book> foundBooks, User expectedUser) {
        Assertions.assertNotNull(foundBooks);
        Assertions.assertEquals(2, foundBooks.size());
        Assertions.assertEquals(foundBooks.get(0).getUser(), expectedUser);
        Assertions.assertEquals(foundBooks.get(1).getUser(), expectedUser);
    }
}