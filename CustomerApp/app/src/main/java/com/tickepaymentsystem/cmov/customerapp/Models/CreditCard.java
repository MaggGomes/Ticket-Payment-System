package com.tickepaymentsystem.cmov.customerapp.Models;

import com.google.gson.annotations.SerializedName;

public class CreditCard {

    @SerializedName("type")
    private String type;
    @SerializedName("number")
    private Integer number;
    @SerializedName("validity")
    private String validity;

    public CreditCard(String type, Integer number, String validity) {
        this.type = type;
        this.number = number;
        this.validity = validity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }
}
