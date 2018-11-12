package com.tickepaymentsystem.cmov.customerapp.Client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit INSTANCE = null;
    private static String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static Retrofit getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return INSTANCE;
    }
}
