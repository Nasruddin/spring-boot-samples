package com.javatab.basicreactiveapi;

import java.util.UUID;

public class Book {

    private final String id;
    private String name;

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public Book(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
