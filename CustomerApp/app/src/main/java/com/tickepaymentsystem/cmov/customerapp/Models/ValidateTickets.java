package com.tickepaymentsystem.cmov.customerapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValidateTickets {

    @SerializedName("tickets")
    public List<Ticket> tickets;
    @SerializedName("userId")
    public String userId;

    public ValidateTickets(List<Ticket> tickets, String userId) {
        this.tickets = tickets;
        this.userId = userId;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
