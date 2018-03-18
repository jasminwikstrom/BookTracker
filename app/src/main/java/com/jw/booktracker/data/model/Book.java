package com.jw.booktracker.data.model;

public class Book {

    private String id;
    private String title;
    private String author;
    private Integer rating;

    public Book() {
    }

    public Book(String id, String title, String author, Integer rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
