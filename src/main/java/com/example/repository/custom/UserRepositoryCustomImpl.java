package com.example.repository.custom;

import com.example.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private EntityManager entityManager;

    @Autowired
    public UserRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findByNameOrLastName(String name, String lastName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> userRoot = cq.from(User.class);
        Predicate namePredicate = cb.equal(userRoot.get("name"), name);
        Predicate lastNamePredicate = cb.equal(userRoot.get("lastName"), lastName);

        Predicate predicate = cb.or(namePredicate, lastNamePredicate);

        cq.select(userRoot).where(predicate);

        return entityManager.createQuery(cq).getResultList();
    }
}
