package com.tickepaymentsystem.cmov.customerapp.Client;

import com.tickepaymentsystem.cmov.customerapp.Models.Responses.ResponsePurchaseTickets;
import com.tickepaymentsystem.cmov.customerapp.Models.Responses.ResponseRegister;
import com.tickepaymentsystem.cmov.customerapp.Models.Requests.RequestMessage;
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
    Call<ResponseRegister> register(@Body User body);

    // Returns all the shows
    @GET("shows")
    Call<List<Show>> getShows();

    // Buy tickets
    @POST("tickets/buy")
    Call<ResponsePurchaseTickets> buyTickets(@Body RequestMessage body);

    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();
}