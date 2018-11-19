package com.tickepaymentsystem.cmov.cafetariaapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tickepaymentsystem.cmov.cafetariaapp.Client.ApiClient;
import com.tickepaymentsystem.cmov.cafetariaapp.Client.DataService;
import com.tickepaymentsystem.cmov.cafetariaapp.Models.RequestCafetariaOrder;
import com.tickepaymentsystem.cmov.cafetariaapp.Models.ResponseCafetariaOrder;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

    }

    private void requestValidadeOrder(RequestCafetariaOrder body){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        DataService service = ApiClient.getInstance().create(DataService.class);
        Call<ResponseCafetariaOrder> call = service.cafetariaOrder(body);

        call.enqueue(new Callback<ResponseCafetariaOrder>() {
            @Override
            public void onResponse(Call<ResponseCafetariaOrder> call, Response<ResponseCafetariaOrder> response) {
                progressDialog.dismiss();

                if(response.isSuccessful()) {
                    /*Singleton.tickets.addAll(response.body().getTickets());
                    Singleton.vouchers.addAll(response.body().getVouchers());

                    Gson gson = new Gson();
                    String tickets = gson.toJson(Singleton.tickets);
                    String vouchers = gson.toJson(Singleton.vouchers);

                    SharedPreferences sharedPref = getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(Constants.TICKETS, tickets);
                    editor.putString(Constants.VOUCHERS, vouchers);
                    editor.apply();*/

                    Toasty.success(getApplicationContext(), "Order validated with success!", Toast.LENGTH_LONG, true).show();
                } else {
                    Toasty.error(getApplicationContext(), "Failed to validate cafetaria order. Please review your order!", Toast.LENGTH_LONG, true).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCafetariaOrder> call, Throwable t) {
                progressDialog.dismiss();
                Toasty.error(getApplicationContext(), "Failed to validate cafetaria order. Please review your order!", Toast.LENGTH_LONG, true).show();
            }
        });
    }
}
