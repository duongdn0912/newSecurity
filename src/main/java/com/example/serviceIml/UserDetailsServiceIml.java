package com.example.serviceIml;


import com.example.model.CurrentUser;
import com.example.model.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by dnduong on 1/11/2017.
 */

@Service
public class UserDetailsServiceIml implements UserDetailsService{

    private final UserService userService;

    @Autowired
    public UserDetailsServiceIml(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", s)));
        return new CurrentUser(user);
    }
}
