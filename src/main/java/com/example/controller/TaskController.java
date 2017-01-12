package com.example.controller;

import com.example.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dnduong on 1/11/2017.
 */

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @RequestMapping(value = "/getTask/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getTask(@PathVariable long id){
        return taskService.getTask(id);
    }

}
