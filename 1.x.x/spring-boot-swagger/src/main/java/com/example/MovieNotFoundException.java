package com.example;

/**
 * Created by nasruddin on 16/10/16.
 */
public class MovieNotFoundException extends RuntimeException {

    private Long id;

    public MovieNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return "Task with ID '" + id + "' not found";
    }
}
