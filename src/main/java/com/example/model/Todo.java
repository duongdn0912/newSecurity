package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "todo")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "todo_id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "desc")
    @NonNull
    private String description;

//    @ManyToMany(targetEntity = Tag.class, mappedBy = "todoList")
    @ManyToMany(mappedBy = "todoList")
//    @ManyToMany  //Unidirectional
    private List<Tag> tagList;

    @ManyToOne
    @NonNull
    private User user;

}
