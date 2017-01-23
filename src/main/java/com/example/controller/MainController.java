package com.example.controller;

import com.example.model.Tag;
import com.example.model.Todo;
import com.example.model.User;
import com.example.repository.TagRepository;
import com.example.repository.TodoRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private UserRepository userRepository;
    private TodoRepository todoRepository;
    private TagRepository tagRepository;

    @Autowired
    public MainController(UserRepository userRepository, TodoRepository todoRepository, TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
        this.tagRepository = tagRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Todo addTodo(@RequestParam String todo, @RequestParam(value = "tagName[]") List<String> tagNames) {
        User user = userRepository.findOne(getCurrentUser().getId());
        Todo todoInfo = new Todo();
        todoInfo.setUser(user);
        todoInfo.setName(todo);
        todoInfo.setDescription(todo);
        Todo todoSaved = todoRepository.save(todoInfo);
        tagNames.forEach(tagName -> addTag(tagName, todoSaved.getId()));
        return todoSaved;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Todo updateTodo(@RequestParam Long todoId,
                           @RequestParam String todoName,
                           @RequestParam String todoDesc,
                           @RequestParam(value = "tagNames[]") List<String> tagNames) {
        Todo toUpdate = todoRepository.findOne(todoId);
        toUpdate.setName(todoName);
        toUpdate.setDescription(todoDesc);
        tagNames.forEach(tagName -> addTag(tagName, toUpdate.getId()));
        return todoRepository.save(toUpdate);
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public String getTodo(Model model, @PathVariable Long id) {
        model.addAttribute("todo", todoRepository.findOne(id));
        return "todo";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String todos(Model model) {
        model.addAttribute("todos", getCurrentUser().getTodos());
        return "myhome";
    }

    @RequestMapping(value = "/deleteTodo", method = RequestMethod.POST)
    @ResponseBody
    public void deleteTodo(@RequestParam Long todoId) {
        todoRepository.delete(todoId);
    }

    @RequestMapping(value = "/deleteTag", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteTag(@RequestParam Long todoId, @RequestParam Long tagId) {
        return removeTagInTodo(todoId, tagId);
    }

    @RequestMapping(value = "/deleteTag/{todoId}/{tagId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean deleteTagGet(@PathVariable Long todoId, @PathVariable Long tagId) {
        return removeTagInTodo(todoId, tagId);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName = auth.getName();

        return userRepository.findByEmail(loggedUserName).get();
    }

    private void addTag(String tagName, Long todoId) {
        Tag tagToAdd = tagRepository.findOneByTagName(tagName);
        if (tagToAdd == null) {
            tagToAdd = new Tag();
            tagToAdd.setTagName(tagName);
        }
        Tag tagSaved = tagRepository.save(tagToAdd);

        Todo todoOwnTag = todoRepository.findOne(todoId);
        if (todoOwnTag.getTags() == null) {
            todoOwnTag.setTags(new ArrayList<>());
        }
        todoOwnTag.getTags().add(tagSaved);
        todoRepository.save(todoOwnTag);
    }

    private Boolean removeTagInTodo(Long todoId, Long tagId) {
        Todo todo = todoRepository.findOne(todoId);
        List<Tag> tags = todo.getTags();
        tags.remove(tags.stream()
                    .filter(tag -> tag.getId() == tagId)
                    .findFirst()
                    .get());

        return todoRepository.save(todo) != null;
    }

}
