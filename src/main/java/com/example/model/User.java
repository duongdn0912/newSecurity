package com.example.model;

import com.example.utility.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public User() {
    }

    public User(String email, String password_hash, Role role) {
        this.email = email;
        this.password_hash = password_hash;
        this.role = role;
    }

    @Column(name = "password_hash", nullable = false)
    private String password_hash;

    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Todo> todoList;

    public Long getId() {
        return id;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return password_hash;
    }

    public void setPasswordHash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
