package com.tickepaymentsystem.cmov.customerapp.Models;

public class Ticket {
    private String id;
    private String name;
    private String description;
    private String date;
    private double price;
    private String imageURL;

    public Ticket(String id, String name, String description, String date, double price, String imageURL){
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.price = price;
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
