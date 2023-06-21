package org.example.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "todos")
@NamedQuery(
        name = "org.example.todo.TodoEntity.findAll",
        query = "SELECT todos FROM TodoEntity todos"
)
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "completed")
    private boolean completed;

    // Constructors, getters, and setters

    public TodoEntity() {
    }

    public TodoEntity(long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
