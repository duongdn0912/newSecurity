package com.example.services;

import com.example.DTO.UserCreateForm;
import com.example.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by dnduong on 1/10/2017.
 */

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUser();

    User create(UserCreateForm userToCreate);

}
