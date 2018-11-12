package com.tickepaymentsystem.cmov.customerapp.Client;

import com.tickepaymentsystem.cmov.customerapp.Models.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClientInterface {

    // TODO - Delete these endpoints after implementing the real ones
    @GET("customers/{username}")
    Call<Customer> getUser(@Path("username") String username);

    @GET("group/{id}/users")
    Call<List<Customer>> groupList(@Path("id") int groupId, @Query("sort") String sort);

    @POST("users/new")
    Call<Customer> createCustomer(@Body Customer customer);
}
