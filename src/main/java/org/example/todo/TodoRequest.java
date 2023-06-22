package org.example.todo;

import jakarta.ws.rs.QueryParam;

public class TodoRequest {
    @QueryParam("title")
    private String title;
    @QueryParam("completed")
    private boolean completed;

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
