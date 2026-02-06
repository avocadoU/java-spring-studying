package com.example.repository;

import com.example.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    private UserRepository userRepository;

    private User user;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void createUser() {
        String userName = UUID.randomUUID().toString();
        String userLastName = UUID.randomUUID().toString();
        String userPassword = UUID.randomUUID().toString();

        user = new User(userName, userLastName, userPassword);
        userRepository.save(user);
    }

    @Test
    void findByNameOrLastNameTest() {
        List<User> foundUsers = userRepository.findByNameOrLastName(user.getName(), null);
        assertFoundUser(foundUsers);

        foundUsers = userRepository.findByNameOrLastName(null, user.getLastName());
        assertFoundUser(foundUsers);
    }

    private void assertFoundUser(List<User> foundUsers) {
        Assertions.assertNotNull(foundUsers);
        Assertions.assertEquals(user.getId(), foundUsers.get(0).getId());
    }
}
