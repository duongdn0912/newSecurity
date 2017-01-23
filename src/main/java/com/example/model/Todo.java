package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    @ManyToMany
    @JoinTable(
            name = "todo_tag",
            joinColumns = @JoinColumn(name = "todoId", referencedColumnName = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "tagId", referencedColumnName = "tag_id")
    )
    @JsonManagedReference
    private List<Tag> tags;

    @ManyToOne
    @NonNull
    @JsonBackReference
    private User user;

}
