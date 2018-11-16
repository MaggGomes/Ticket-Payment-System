package com.tickepaymentsystem.cmov.customerapp.Models;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("userId")
    private String userId;
    @SerializedName("id")
    private Integer id;
    @SerializedName("date")
    private String date;
    @SerializedName("quantity")
    private Integer quantity;

    public Message(String userId, Integer id, String date, Integer quantity) {
        this.userId = userId;
        this.id = id;
        this.date = date;
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
