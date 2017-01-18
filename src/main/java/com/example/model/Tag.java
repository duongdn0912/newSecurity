package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
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

    @ManyToMany
    @JoinTable(
            name = "tag_todo",
            joinColumns = @JoinColumn(name = "tagId", referencedColumnName = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "todoId", referencedColumnName = "todo_id")
    )
    @NonNull
    @JsonBackReference
    private List<Todo> todos;

}
