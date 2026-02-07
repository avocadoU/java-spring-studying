package com.example.repository.custom;

import com.example.entity.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {
    private EntityManager entityManager;

    @Autowired
    public AuthorRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Author> findByNameOrLastName(String name, String lastName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);

        Root<Author> authorRoot = cq.from(Author.class);
        Predicate namePredicate = cb.equal(authorRoot.get("name"), name);
        Predicate lastNamePredicate = cb.equal(authorRoot.get("lastName"), lastName);

        Predicate predicate = cb.or(namePredicate, lastNamePredicate);

        cq.select(authorRoot).where(predicate);

        return entityManager.createQuery(cq).getResultList();
    }
}
