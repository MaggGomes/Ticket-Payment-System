package com.tickepaymentsystem.cmov.customerapp.Client;

import com.tickepaymentsystem.cmov.customerapp.Models.RegisterPojo;
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
    Call<RegisterPojo> register(@Body User body);

    // Returns all the shows
    @GET("shows")
    Call<List<Show>> getShows();

    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();
}