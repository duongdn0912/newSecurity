package com.example;

import com.example.Repository.TagRepository;
import com.example.Repository.TodoRepository;
import com.example.Repository.UserRepository;
import com.example.model.Tag;
import com.example.model.Todo;
import com.example.model.User;
import com.example.utility.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

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
            User user1 = new User();
            user1.setEmail("Jack");
            user1.setPasswordHash("Back");
            user1.setRole(Role.AMDIN);
            userRepository.save(user1);

//            Tag tag1 = new Tag();
//            tag1.setTagName("tag 1");
//            List<Tag> tagList = new ArrayList<>();
//            tagList.add(tag1);
//            tagRepository.save(tag1);

            Todo todo1 = new Todo();
            todo1.setName("todo1");
            todo1.setDescription("this is todo 1");
            todo1.setUser(user1);
//            todo1.setTagList(new ArrayList<>());
//            todo1.getTagList().add(tag1);
//            todo1.setTagList(tagList);
            todoRepository.save(todo1);

            Tag tag1 = new Tag();
            tag1.setTagName("tag 1");
//            List<Tag> tagList = new ArrayList<>();
//            tagList.add(tag1);
            tag1.setTodoList(new ArrayList<>());
            tag1.getTodoList().add(todo1);
            tagRepository.save(tag1);

//            Todo todo2 = new Todo();
//            todo2.setName("todo2");
//            todo2.setDescription("this is todo 2");
//            todo2.setUser(user1);
////            todo1.setTagList(new ArrayList<>());
////            todo1.getTagList().add(tag1);
//            todo2.setTagList(tagList);
//            todoRepository.save(todo2);

        };
    }

}
