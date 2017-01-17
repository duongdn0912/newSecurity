package com.example.controller;

import com.example.DTO.UserCreateForm;
import com.example.Repository.UserRepository;
import com.example.utility.UserCreateFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
public class UserController {

    private final UserCreateFormValidator userCreateFormValidator;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository, UserCreateFormValidator userCreateFormValidator){
        this.userRepository = userRepository;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(userCreateFormValidator);
    }

    @RequestMapping("/user/{id}")
    public ModelAndView showUser(@PathVariable Long id){
        return new ModelAndView("users", "users", userRepository.findOneById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage(){
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

//    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
//    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "user_create";
//        }
//        try {
//            userRepository.save(form);
//        } catch (DataIntegrityViolationException e) {
//            bindingResult.reject("email.exists", "Email already exists");
//            return "user_create";
//        }
//        return "redirect:/users";
//    }


}
