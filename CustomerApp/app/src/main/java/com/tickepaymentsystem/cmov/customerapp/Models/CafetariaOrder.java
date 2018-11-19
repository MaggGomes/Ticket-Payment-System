package com.tickepaymentsystem.cmov.customerapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CafetariaOrder {

    @SerializedName("products")
    private List<Product> products;
    @SerializedName("userId")
    private String userId;
    @SerializedName("vouchers")
    private List<Voucher> vouchers;

    public CafetariaOrder(List<Product> products, String userId, List<Voucher> vouchers) {
        this.products = products;
        this.userId = userId;
        this.vouchers = vouchers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Voucher> vouchers) {
        this.vouchers = vouchers;
    }
}
