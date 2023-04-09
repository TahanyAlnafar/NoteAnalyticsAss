package com.example.noteanalyticsass;


import java.io.Serializable;

class Category implements Serializable {
    String id;
    String title;

    private  Category(){}
    Category(String id, String title) {
        this.id = id;
        this.title = title;


    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}