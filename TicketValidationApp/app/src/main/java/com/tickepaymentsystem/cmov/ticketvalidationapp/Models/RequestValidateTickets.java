package com.tickepaymentsystem.cmov.ticketvalidationapp.Models;

import com.google.gson.annotations.SerializedName;

public class RequestValidateTickets {

    @SerializedName("message")
    private ValidateTickets message;
    @SerializedName("messageSigned")
    private String messageSigned;

    public RequestValidateTickets(ValidateTickets message, String messageSigned) {
        this.message = message;
        this.messageSigned = messageSigned;
    }

    public ValidateTickets getMessage() {
        return message;
    }

    public void setMessage(ValidateTickets message) {
        this.message = message;
    }

    public String getMessageSigned() {
        return messageSigned;
    }

    public void setMessageSigned(String messageSigned) {
        this.messageSigned = messageSigned;
    }
}
