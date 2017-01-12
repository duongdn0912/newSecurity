package com.example.serviceIml;

import com.example.DTO.UserCreateForm;
import com.example.Repository.UserRepository;
import com.example.model.User;
import com.example.services.UserService;
import com.example.utility.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by dnduong on 1/10/2017.
 */

@Service
public class UserServiceIml implements UserService{
;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceIml(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        user.setPasswordHash("aaa");
        user.setRole(Role.USER);
        user.setId((long) 1);
        return Optional.of(user);
//        return userRepository.findOneByEmail(email);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserCreateForm userToCreate) {
        User user = new User();
        user.setRole(userToCreate.getRole());
        user.setEmail(userToCreate.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(userToCreate.getPassword()));
        return userRepository.save(user);
    }
}
