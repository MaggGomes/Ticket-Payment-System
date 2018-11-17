package com.tickepaymentsystem.cmov.customerapp.Models;

import com.google.gson.annotations.SerializedName;

public class ValidateTickets {

    @SerializedName("ids")
    public String ids;
    @SerializedName("userId")
    public String userId;

    public ValidateTickets(String ids, String userId) {
        this.ids = ids;
        this.userId = userId;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
