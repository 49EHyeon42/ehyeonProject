package dev.ehyeon.ehyeon;

public class Todo {

    private Long id;
    private String title;
    private String description;
    private boolean status;

    public Todo() {
    }

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return status;
    }

    public void setCompleted(boolean completed) {
        status = completed;
    }
}
