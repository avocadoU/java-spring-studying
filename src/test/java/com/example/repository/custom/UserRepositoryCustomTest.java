package com.example.repository.custom;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
public class UserRepositoryCustomTest {

    private UserRepository userRepository;

    private UserRepositoryCustom userRepositoryCustom;

    @Autowired
    public UserRepositoryCustomTest(UserRepository userRepository, UserRepositoryCustom userRepositoryCustom) {
        this.userRepository = userRepository;
        this.userRepositoryCustom = userRepositoryCustom;
    }

    User createUser() {
        String userName = UUID.randomUUID().toString();
        String userLastName = UUID.randomUUID().toString();
        String userPassword = UUID.randomUUID().toString();
        User user = new User(userName, userLastName, userPassword);

        userRepository.save(user);
        return user;
    }

    @Test
    void findByNameOrLastNameTest() {
        User user1 = createUser();
        User user2 = createUser();

        List<User> foundUsers = userRepositoryCustom.findByNameOrLastName(user1.getName(), null);
        assertFoundUser(foundUsers, user1);

        foundUsers = userRepositoryCustom.findByNameOrLastName(null, user1.getLastName());
        assertFoundUser(foundUsers, user1);

        foundUsers = userRepositoryCustom.findByNameOrLastName(user2.getName(), null);
        assertFoundUser(foundUsers, user2);

        foundUsers = userRepositoryCustom.findByNameOrLastName(null, user2.getLastName());
        assertFoundUser(foundUsers, user2);
    }

    private void assertFoundUser(List<User> foundUsers, User expectedUser) {
        Assertions.assertNotNull(foundUsers);
        Assertions.assertEquals(1, foundUsers.size());
        Assertions.assertEquals(expectedUser.getId(), foundUsers.get(0).getId());
    }
}
