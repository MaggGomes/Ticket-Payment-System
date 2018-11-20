package com.tickepaymentsystem.cmov.customerapp.Models.Responses;

import com.google.gson.annotations.SerializedName;
import com.tickepaymentsystem.cmov.customerapp.Models.OrderTransaction;
import com.tickepaymentsystem.cmov.customerapp.Models.Ticket;
import com.tickepaymentsystem.cmov.customerapp.Models.TicketTransaction;
import com.tickepaymentsystem.cmov.customerapp.Models.Voucher;

import java.util.List;

public class ResponseTransactions {
    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("ticketTransactions")
    private List<TicketTransaction> ticketTransactions;
    @SerializedName("orderTransactions")
    private List<OrderTransaction> orderTransactions;
    @SerializedName("validTickets")
    private List<Ticket> validTickets;
    @SerializedName("validVouchers")
    private List<Voucher> validVouchers;

    public ResponseTransactions(Boolean success, String message, List<TicketTransaction> ticketTransactions, List<OrderTransaction> orderTransactions, List<Ticket> validTickets, List<Voucher> validVouchers) {
        this.success = success;
        this.message = message;
        this.ticketTransactions = ticketTransactions;
        this.orderTransactions = orderTransactions;
        this.validTickets = validTickets;
        this.validVouchers = validVouchers;
    }

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

    public List<TicketTransaction> getTicketTransactions() {
        return ticketTransactions;
    }

    public void setTicketTransactions(List<TicketTransaction> ticketTransactions) {
        this.ticketTransactions = ticketTransactions;
    }

    public List<OrderTransaction> getOrderTransactions() {
        return orderTransactions;
    }

    public void setOrderTransactions(List<OrderTransaction> orderTransactions) {
        this.orderTransactions = orderTransactions;
    }

    public List<Ticket> getValidTickets() {
        return validTickets;
    }

    public void setValidTickets(List<Ticket> validTickets) {
        this.validTickets = validTickets;
    }

    public List<Voucher> getValidVouchers() {
        return validVouchers;
    }

    public void setValidVouchers(List<Voucher> validVouchers) {
        this.validVouchers = validVouchers;
    }
}
