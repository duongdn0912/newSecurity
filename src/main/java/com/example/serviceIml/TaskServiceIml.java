package com.example.serviceIml;

import com.example.services.TaskService;
import org.springframework.stereotype.Service;

/**
 * Created by dnduong on 1/11/2017.
 */

@Service
public class TaskServiceIml implements TaskService {

    @Override
    public String getTask(long id) {
        return "this is task " + id;
    }
}
