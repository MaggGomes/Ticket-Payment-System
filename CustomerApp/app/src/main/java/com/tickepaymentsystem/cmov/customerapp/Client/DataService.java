package com.tickepaymentsystem.cmov.customerapp.Client;

import com.tickepaymentsystem.cmov.customerapp.Models.PurchaseTicketsResponse;
import com.tickepaymentsystem.cmov.customerapp.Models.RegisterResponse;
import com.tickepaymentsystem.cmov.customerapp.Models.RequestMessage;
import com.tickepaymentsystem.cmov.customerapp.Models.RetroPhoto;
import com.tickepaymentsystem.cmov.customerapp.Models.Show;
import com.tickepaymentsystem.cmov.customerapp.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    // Register an user
    @POST("users/register")
    Call<RegisterResponse> register(@Body User body);

    // Returns all the shows
    @GET("shows")
    Call<List<Show>> getShows();

    // Buy tickets
    @POST("tickets/buy")
    Call<PurchaseTicketsResponse> buyTickets(@Body RequestMessage body);

    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();
}