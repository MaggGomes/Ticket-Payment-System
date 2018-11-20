package com.tickepaymentsystem.cmov.customerapp.Client;

import com.tickepaymentsystem.cmov.customerapp.Models.Requests.RequestValidateTickets;
import com.tickepaymentsystem.cmov.customerapp.Models.Responses.ResponseBuyTickets;
import com.tickepaymentsystem.cmov.customerapp.Models.Responses.ResponseRegister;
import com.tickepaymentsystem.cmov.customerapp.Models.Requests.RequestBuyTickets;
import com.tickepaymentsystem.cmov.customerapp.Models.Responses.ResponseTransactions;
import com.tickepaymentsystem.cmov.customerapp.Models.Show;
import com.tickepaymentsystem.cmov.customerapp.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {

    // Register an user
    @POST("users/register")
    Call<ResponseRegister> register(@Body User body);

    // Returns all the shows
    @GET("shows")
    Call<List<Show>> getShows();

    // Buy tickets
    @POST("tickets/buy")
    Call<ResponseBuyTickets> buyTickets(@Body RequestBuyTickets body);

    // Get transactions
    @GET("transactions/{id}")
    Call<ResponseTransactions> getTransactions(@Path("id") String id);
}