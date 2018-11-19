package com.tickepaymentsystem.cmov.customerapp.Models;

public class OrderVoucher {

    private int id;
    private int maxAvailable;
    private int quantity;

    public OrderVoucher(int id, int maxAvailable, int quantity) {
        this.id = id;
        this.maxAvailable = maxAvailable;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxAvailable() {
        return maxAvailable;
    }

    public void setMaxAvailable(int maxAvailable) {
        this.maxAvailable = maxAvailable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
