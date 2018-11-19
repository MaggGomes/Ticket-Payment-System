package com.tickepaymentsystem.cmov.customerapp.Models.Requests;

import com.google.gson.annotations.SerializedName;
import com.tickepaymentsystem.cmov.customerapp.Models.CafetariaOrder;

public class RequestCafetariaOrder {

    @SerializedName("message")
    private CafetariaOrder message;
    @SerializedName("messageSigned")
    private String messageSigned;

    public RequestCafetariaOrder(CafetariaOrder message, String messageSigned) {
        this.message = message;
        this.messageSigned = messageSigned;
    }

    public CafetariaOrder getMessage() {
        return message;
    }

    public void setMessage(CafetariaOrder message) {
        this.message = message;
    }

    public String getMessageSigned() {
        return messageSigned;
    }

    public void setMessageSigned(String messageSigned) {
        this.messageSigned = messageSigned;
    }
}
