package org.example.todo;

public class TodoRepresentation {

    private String title;
    private boolean completed;

    public TodoRepresentation() {
    }

    public TodoRepresentation(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public TodoRepresentation(TodoEntity todo) {
        title = todo.getTitle();
        completed = todo.isCompleted();
    }

    public TodoEntity toTodoEntity() {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(title);
        todoEntity.setCompleted(completed);
        return todoEntity;
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
