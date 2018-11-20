package com.tickepaymentsystem.cmov.cafetariaapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCafetariaOrder {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("products")
    private List<Product> products;
    @SerializedName("vouchers")
    private List<Voucher> vouchers;
    @SerializedName("order")
    private OrderTransaction order;
    @SerializedName("totalPrice")
    private Double totalPrice;
    @SerializedName("userNif")
    private Integer userNif;

    public ResponseCafetariaOrder(Boolean success, String message, List<Product> products, List<Voucher> vouchers, OrderTransaction order, Double totalPrice, Integer userNif) {
        this.success = success;
        this.message = message;
        this.products = products;
        this.vouchers = vouchers;
        this.order = order;
        this.totalPrice = totalPrice;
        this.userNif = userNif;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    public OrderTransaction getOrder() {
        return order;
    }

    public void setOrder(OrderTransaction order) {
        this.order = order;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getUserNif() {
        return userNif;
    }

    public void setUserNif(Integer userNif) {
        this.userNif = userNif;
    }
}
