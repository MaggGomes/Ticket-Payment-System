package com.tickepaymentsystem.cmov.customerapp.Models;

import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("id")
    private String id;
    @SerializedName("seatNumber")
    private Integer seatNumber;
    @SerializedName("showId")
    private Integer showId;
    @SerializedName("userId")
    private String userId;
    @SerializedName("showName")
    private String showName;
    @SerializedName("showDate")
    private String showDate;

    public Ticket(String id, Integer seatNumber, Integer showId, String userId, String showName, String showDate){
        this.id = id;
        this.seatNumber = seatNumber;
        this.showId = showId;
        this.userId = userId;
        this.showName = showName;
        this.showDate = showDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showDate) {
        this.showName = showName;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }
}
