package com.example.repository;

import com.example.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.List;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User createUser() {
        String userName = UUID.randomUUID().toString();
        String userLastName = UUID.randomUUID().toString();
        String userPassword = UUID.randomUUID().toString();

        User user = new User(userName, userLastName, userPassword);
        userRepository.save(user);

        return user;
    }

    @Test
    void findByNameTest() {
        User user = createUser();

        User foundUser = userRepository.findByName(user.getName()).get(0);

        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(user.getId(), foundUser.getId());
        Assertions.assertEquals(user.getName(), foundUser.getName());
    }

    @Test
    void findByLastName() {
        User user = createUser();

        User foundUser = userRepository.findByLastName(user.getLastName()).get(0);

        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(user.getId(), foundUser.getId());
        Assertions.assertEquals(user.getLastName(), foundUser.getLastName());
    }
}
