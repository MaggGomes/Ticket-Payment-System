package com.tickepaymentsystem.cmov.customerapp.Models.Requests;

import com.google.gson.annotations.SerializedName;
import com.tickepaymentsystem.cmov.customerapp.Models.Message;

public class RequestMessage {

    @SerializedName("message")
    private Message message;
    @SerializedName("messageSigned")
    private String messageSigned;

    public RequestMessage(Message message, String messageSigned){
        this.message = message;
        this.messageSigned = messageSigned;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getMessageSigned() {
        return messageSigned;
    }

    public void setMessageSigned(String messageSigned) {
        this.messageSigned = messageSigned;
    }
}
