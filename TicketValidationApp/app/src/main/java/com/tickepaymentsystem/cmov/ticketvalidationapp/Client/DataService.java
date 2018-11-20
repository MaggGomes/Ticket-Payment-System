package com.tickepaymentsystem.cmov.ticketvalidationapp.Client;

import com.tickepaymentsystem.cmov.ticketvalidationapp.Models.RequestValidateTickets;
import com.tickepaymentsystem.cmov.ticketvalidationapp.Models.ResponseValidateTickets;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface DataService {

    // Validate tickets
    @PUT("tickets/validate")
    Call<ResponseValidateTickets> validateTickets(@Body RequestValidateTickets body);
}