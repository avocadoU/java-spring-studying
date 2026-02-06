package com.example.repository;

import com.example.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findByLastName(String lastName);
}
