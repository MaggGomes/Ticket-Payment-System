package com.tickepaymentsystem.cmov.customerapp;

import com.tickepaymentsystem.cmov.customerapp.Models.Show;

import java.util.List;

public class Singleton {

    public static List<Show> shows;

    private static Singleton INSTANCE = null;

    private Singleton() {}

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }

        return INSTANCE;
    }
}
