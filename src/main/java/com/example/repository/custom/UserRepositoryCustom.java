package com.example.repository.custom;


import com.example.entity.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findByNameOrLastName(String name, String lastName);
}
