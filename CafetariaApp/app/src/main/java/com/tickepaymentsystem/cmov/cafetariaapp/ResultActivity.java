package com.tickepaymentsystem.cmov.cafetariaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tickepaymentsystem.cmov.cafetariaapp.Client.ApiClient;
import com.tickepaymentsystem.cmov.cafetariaapp.Client.DataService;
import com.tickepaymentsystem.cmov.cafetariaapp.Models.RequestCafetariaOrder;
import com.tickepaymentsystem.cmov.cafetariaapp.Models.ResponseCafetariaOrder;
import com.tickepaymentsystem.cmov.cafetariaapp.Utils.Constants;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String qrcodeString = bundle.getString(Constants.VALIDATION);

            Gson gson = new Gson();

            RequestCafetariaOrder requestCafetariaOrder = gson.fromJson(qrcodeString, new TypeToken<RequestCafetariaOrder>(){}.getType());
            validateTickets(requestCafetariaOrder);
        }
    }

    public void validateTickets(RequestCafetariaOrder requestCafetariaOrder){
        DataService service = ApiClient.getInstance().create(DataService.class);
        Call<ResponseCafetariaOrder> call = service.cafetariaOrder(requestCafetariaOrder);
        call.enqueue(new Callback<ResponseCafetariaOrder>() {
            @Override
            public void onResponse(Call<ResponseCafetariaOrder> call, Response<ResponseCafetariaOrder> response) {
               /* if(response.body().getSuccess()){
                    Toasty.success(getApplicationContext(), "Success validating cafetaria order", Toast.LENGTH_LONG, true).show();
                } else {
                    Toasty.error(getApplicationContext(), "Success validating cafetaria order", Toast.LENGTH_LONG, true).show();
                }*/

                Toasty.success(getApplicationContext(), response.message(), Toast.LENGTH_LONG, true).show();
            }

            @Override
            public void onFailure(Call<ResponseCafetariaOrder> call, Throwable t) {
                Toasty.error(getApplicationContext(), "Failed validating cafetaria order", Toast.LENGTH_LONG, true).show();
            }
        });
    }
}
