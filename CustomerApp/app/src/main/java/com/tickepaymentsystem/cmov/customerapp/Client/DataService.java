package com.tickepaymentsystem.cmov.customerapp.Client;

import com.tickepaymentsystem.cmov.customerapp.Models.RetroPhoto;
import com.tickepaymentsystem.cmov.customerapp.Models.Show;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("shows")
    Call<List<Show>> getShows();

    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();
}