package com.tickepaymentsystem.cmov.customerapp.Models.Responses;

import com.google.gson.annotations.SerializedName;
import com.tickepaymentsystem.cmov.customerapp.Models.Ticket;

import java.util.List;

public class ResponseValidateTickets {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private Boolean message;
    @SerializedName("invalidTickets")
    private List<Ticket> invalidTickets;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

    public List<Ticket> getInvalidTickets() {
        return invalidTickets;
    }

    public void setInvalidTickets(List<Ticket> invalidTickets) {
        this.invalidTickets = invalidTickets;
    }
}
