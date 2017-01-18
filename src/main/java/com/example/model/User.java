package com.example.model;

import com.example.utility.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    @NonNull
    private String email;

    @Column(name = "password_hash", nullable = false)
    @NonNull
    private String password_hash;

    @Column(name = "role")
    @NonNull
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Todo> todoList;

}
