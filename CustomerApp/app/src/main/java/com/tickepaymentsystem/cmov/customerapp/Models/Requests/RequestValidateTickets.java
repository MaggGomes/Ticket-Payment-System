package com.tickepaymentsystem.cmov.customerapp.Models.Requests;

import com.google.gson.annotations.SerializedName;
import com.tickepaymentsystem.cmov.customerapp.Models.ValidateTickets;

public class RequestValidateTickets {

    @SerializedName("message")
    private ValidateTickets message;
    //@SerializedName("messageSigned")
    //private String messageSigned;


    public RequestValidateTickets(ValidateTickets message) {
        this.message = message;
    }

    public ValidateTickets getMessage() {
        return message;
    }

    public void setMessage(ValidateTickets message) {
        this.message = message;
    }
}
