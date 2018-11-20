package com.tickepaymentsystem.cmov.customerapp.Models;

import com.google.gson.annotations.SerializedName;

public class TicketTransaction {

    @SerializedName("id")
    private Integer id;
    @SerializedName("userId")
    private String userId;
    @SerializedName("showId")
    private Integer showId;
    @SerializedName("showName")
    private String showName;
    @SerializedName("showDescription")
    private String showDescription;
    @SerializedName("noTickets")
    private Integer noTickets;
    @SerializedName("date")
    private String date;
    @SerializedName("price")
    private Double price;
    @SerializedName("totalPrice")
    private Double totalPrice;

    public TicketTransaction(Integer id, String userId, Integer showId, String showName, String showDescription, Integer noTickets, String date, Double price, Double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.showId = showId;
        this.showName = showName;
        this.showDescription = showDescription;
        this.noTickets = noTickets;
        this.date = date;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowDescription() {
        return showDescription;
    }

    public void setShowDescription(String showDescription) {
        this.showDescription = showDescription;
    }

    public Integer getNoTickets() {
        return noTickets;
    }

    public void setNoTickets(Integer noTickets) {
        this.noTickets = noTickets;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
