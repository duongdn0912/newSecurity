package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String tagName;

//    @ManyToMany(targetEntity = Todo.class)
//    @ManyToMany(cascade = CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name="tag_todo",
            joinColumns=@JoinColumn(name="tag_id", referencedColumnName="tag_id"),
            inverseJoinColumns=@JoinColumn(name="todo_id", referencedColumnName="todo_id")
    )
    @NonNull
    private List<Todo> todoList;

}
