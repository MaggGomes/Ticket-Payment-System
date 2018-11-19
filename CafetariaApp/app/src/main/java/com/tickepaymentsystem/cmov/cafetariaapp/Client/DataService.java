package com.tickepaymentsystem.cmov.cafetariaapp.Client;

import com.tickepaymentsystem.cmov.cafetariaapp.Models.RequestCafetariaOrder;
import com.tickepaymentsystem.cmov.cafetariaapp.Models.ResponseCafetariaOrder;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface DataService {

    // Make cafetaria order
    @PUT("orders/buy")
    Call<ResponseCafetariaOrder> cafetariaOrder(@Body RequestCafetariaOrder body);
}