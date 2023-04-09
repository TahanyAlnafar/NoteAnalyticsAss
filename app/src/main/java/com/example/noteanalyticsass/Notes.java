package com.example.noteanalyticsass;


import java.io.Serializable;

public class Notes implements Serializable {
    String id;
    String name;
    String category;


    private  Notes(){}
    Notes(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}