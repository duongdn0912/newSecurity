package com.example.controller;

import com.example.model.Tag;
import com.example.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TagController {

    private TagRepository tagRepository;

    @Autowired
    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @RequestMapping(value = "/tag/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Tag getTodosByTagId(@PathVariable Long id) {
        return tagRepository.findOne(id);
    }

}
