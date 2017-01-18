package com.example.controller;

import com.example.model.Todo;
import com.example.repository.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TodoController {

    private TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @RequestMapping(value = "/todosByUserId/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Todo> getTodoListByUserId(@PathVariable Long userId) {
        return todoRepository.findByUserId(userId);
    }

    @RequestMapping(value = "/todoById/{todoId}", method = RequestMethod.GET)
    @ResponseBody
    public Todo getTodoById(@PathVariable Long todoId) {
        return todoRepository.findOne(todoId);
    }

}
