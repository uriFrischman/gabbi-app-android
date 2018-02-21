package com.frischman.uri.gabbiapp.model;


public class ShabbosZman {

    String date;
    String category;
    String title;

    public ShabbosZman(String date, String category, String title) {
        this.date = date;
        this.category = category;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
