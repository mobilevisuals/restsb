package com.example.resttest.models;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

public class Book extends RepresentationModel implements Serializable {

    private String title;
    private String author;
    private int id;
    private static final long serialVersionUID=1;

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
