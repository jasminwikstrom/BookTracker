package com.jw.booktracker.data.model;

import android.os.Parcelable;

import java.io.Serializable;

public class Book implements Serializable{

    private String id;
    private String title;
    private String author;
    private String note;
    private String imageUrl;
    private Integer rating;

    public Book() {
    }

    public Book(
        String id,
        String title,
        String author,
        String note,
        String imageUrl,
        Integer rating
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.note = note;
        this.imageUrl = imageUrl;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
