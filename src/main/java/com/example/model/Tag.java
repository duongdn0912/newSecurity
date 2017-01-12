package com.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dnduong on 1/12/2017.
 */
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String tagName;

//    @ManyToMany(mappedBy = "tagList")
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name="tag_todo",
//            joinColumns=@JoinColumn(name="tag_id", referencedColumnName="tag_id"),
//            inverseJoinColumns=@JoinColumn(name="todo_id", referencedColumnName="todo_id")
//    )
    private List<Todo> todoList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="tag_todo",
            joinColumns=@JoinColumn(name="tag_id", referencedColumnName="tag_id"),
            inverseJoinColumns=@JoinColumn(name="todo_id", referencedColumnName="todo_id")
    )
    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

}
