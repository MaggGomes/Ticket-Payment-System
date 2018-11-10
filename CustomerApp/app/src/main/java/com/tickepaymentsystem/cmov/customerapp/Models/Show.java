package com.tickepaymentsystem.cmov.customerapp.Models;

public class Show {

    private int id;
    private String name;
    private String description;
    private String date;
    private double price;
    private String imageURL;

    public Show(int id, String name, String description, String date, double price, String imageURL){
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.price = price;
        this.imageURL = imageURL;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }
}
