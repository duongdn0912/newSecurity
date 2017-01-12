//package com.example.Repository;
//
//import com.example.model.User;
//import com.example.utility.Role;
//
//import java.util.Optional;
//
///**
// * Created by dnduong on 1/11/2017.
// */
//public class UserRepoIml implements UserRepository{
//
//    @Override
//    public Optional<User> findOneByEmail(String email) {
//        User user = new User();
//        user.setEmail("duong@gmail.com");
//        user.setPasswordHash("thisispass");
//        user.setRole(Role.USER);
//        return Optional.ofNullable(user);
//    }
//}
