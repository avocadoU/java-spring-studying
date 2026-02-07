package com.example.repository.custom;

import com.example.entity.Author;
import com.example.entity.Book;
import com.example.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private EntityManager entityManager;

    @Autowired
    public BookRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Book> findByTitle(String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> bookRoot = cq.from(Book.class);
        Predicate titlePredicate = cb.equal(bookRoot.get("title"), title);

        cq.select(bookRoot).where(titlePredicate);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Book> findByAuthorNameOrLastName(String name, String lastName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> bookRoot = cq.from(Book.class);
        Join<Book, Author> author = bookRoot.join("author", JoinType.INNER);

        Predicate namePredicate = cb.equal(author.get("name"), name);
        Predicate lastNamePredicate = cb.equal(author.get("lastName"), lastName);
        Predicate predicate = cb.or(namePredicate, lastNamePredicate);

        cq.select(bookRoot).where(predicate);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Book> findByUserNameOrLastName(String name, String lastName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> bookRoot = cq.from(Book.class);
        Join<Book, User> user = bookRoot.join("user", JoinType.INNER);

        Predicate namePredicate = cb.equal(user.get("name"), name);
        Predicate lastNamePredicate = cb.equal(user.get("lastName"), lastName);
        Predicate predicate = cb.or(namePredicate, lastNamePredicate);

        cq.select(bookRoot).where(predicate);

        return entityManager.createQuery(cq).getResultList();
    }
}
