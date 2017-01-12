package com.example.controller;

import com.example.DTO.UserCreateForm;
import com.example.services.UserService;
import com.example.utility.UserCreateFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * Created by dnduong on 1/10/2017.
 */

@Controller
public class UserController {

    private final UserCreateFormValidator userCreateFormValidator;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator){
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(userCreateFormValidator);
    }

    @RequestMapping("/user/{id}")
    public ModelAndView showUser(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("users", "users", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
        return modelAndView;
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage(){
        ModelAndView userCreatePage = new ModelAndView("user_create", "form", new UserCreateForm());
        return userCreatePage;
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("email.exists", "Email already exists");
            return "user_create";
        }
        return "redirect:/users";
    }


}
