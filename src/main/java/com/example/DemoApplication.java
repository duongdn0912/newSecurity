package com.example;

import com.example.Repository.TagRepository;
import com.example.Repository.TodoRepository;
import com.example.Repository.UserRepository;
import com.example.model.Tag;
import com.example.model.Todo;
import com.example.model.User;
import com.example.utility.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, TodoRepository todoRepository, TagRepository tagRepository) {
        return (args) -> {

            // save a couple of customers
            User usera = userRepository.save(new User(   "a",     "a",Role.AMDIN));
            User user1 = userRepository.save(new User("Dinh", "Duong",Role.AMDIN));
            User user2 = userRepository.save(new User("Jack", "Blackk",Role.USER));
            User user3 = userRepository.save(new User("Bang", "Kieuuu",Role.USER));
            User user4 = userRepository.save(new User("Quyn", "Tutoti",Role.USER));
            User user5 = userRepository.save(new User("Boon", "Phuott",Role.USER));

            //save some todo
            Todo todo1 = todoRepository.save(new Todo("todo1", "this is todo 1", user1));
            Todo todo2 = todoRepository.save(new Todo("todo2", "this is todo 2", user2));
            Todo todo3 = todoRepository.save(new Todo("todo3", "this is todo 3", user3));
            Todo todo4 = todoRepository.save(new Todo("todo4", "this is todo 4", user4));
            Todo todo5 = todoRepository.save(new Todo("todo5", "this is todo 5", user5));
            Todo todo6 = todoRepository.save(new Todo("todo6", "this is todo 6", user1));
            Todo todo7 = todoRepository.save(new Todo("todo7", "this is todo 7", user2));

            List<Todo> todoList1 = new ArrayList<>();
            todoList1.add(todo1);
            todoList1.add(todo2);
            todoList1.add(todo3);

            List<Todo> todoList2 = new ArrayList<>();
            todoList2.add(todo4);
            todoList2.add(todo5);
            todoList2.add(todo6);

            List<Todo> todoList3 = new ArrayList<>();
            todoList3.add(todo7);

            tagRepository.save(new Tag("tag1", todoList1));
            tagRepository.save(new Tag("tag2", todoList2));
            tagRepository.save(new Tag("tag3", todoList3));
            tagRepository.save(new Tag("tag4", todoList2));

        };
    }

}
