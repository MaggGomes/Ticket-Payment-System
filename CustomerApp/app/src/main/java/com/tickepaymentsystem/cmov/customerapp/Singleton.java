package com.tickepaymentsystem.cmov.customerapp;

import android.content.SharedPreferences;

import com.tickepaymentsystem.cmov.customerapp.Models.OrderVoucher;
import com.tickepaymentsystem.cmov.customerapp.Models.Product;
import com.tickepaymentsystem.cmov.customerapp.Models.Show;
import com.tickepaymentsystem.cmov.customerapp.Models.Ticket;
import com.tickepaymentsystem.cmov.customerapp.Models.Voucher;

import java.util.List;

public class Singleton {

    public static String userUUID;
    public static String userName;
    public static List<Show> shows;
    public static List<Ticket> tickets;
    public static List<Voucher> vouchers;
    public static List<Product> products;
    public static List<OrderVoucher> orderVouchers;

    private static Singleton INSTANCE = null;

    private Singleton() {}

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }

        return INSTANCE;
    }
}
