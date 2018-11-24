package com.tickepaymentsystem.cmov.ticketvalidationapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseValidateTickets {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("invalidTickets")
    private List<Ticket> invalidTickets;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Ticket> getInvalidTickets() {
        return invalidTickets;
    }

    public void setInvalidTickets(List<Ticket> invalidTickets) {
        this.invalidTickets = invalidTickets;
    }
}
