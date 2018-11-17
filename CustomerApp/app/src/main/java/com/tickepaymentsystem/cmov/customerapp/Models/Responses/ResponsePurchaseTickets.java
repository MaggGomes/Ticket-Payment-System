package com.tickepaymentsystem.cmov.customerapp.Models.Responses;

import com.google.gson.annotations.SerializedName;
import com.tickepaymentsystem.cmov.customerapp.Models.Ticket;
import com.tickepaymentsystem.cmov.customerapp.Models.Voucher;

import java.util.List;

public class ResponsePurchaseTickets {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("tickets")
    private List<Ticket> tickets;
    @SerializedName("vouchers")
    private List<Voucher> vouchers;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Voucher> vouchers) {
        this.vouchers = vouchers;
    }
}
