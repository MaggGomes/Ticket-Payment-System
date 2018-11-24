package com.tickepaymentsystem.cmov.ticketvalidationapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tickepaymentsystem.cmov.ticketvalidationapp.Client.ApiClient;
import com.tickepaymentsystem.cmov.ticketvalidationapp.Client.DataService;
import com.tickepaymentsystem.cmov.ticketvalidationapp.Models.RequestValidateTickets;
import com.tickepaymentsystem.cmov.ticketvalidationapp.Models.ResponseValidateTickets;
import com.tickepaymentsystem.cmov.ticketvalidationapp.Utils.Constants;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        text = findViewById(R.id.text_result);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String qrcodeString = bundle.getString(Constants.VALIDATION);

            Gson gson = new Gson();

            RequestValidateTickets requestValidateTickets = gson.fromJson(qrcodeString, new TypeToken<RequestValidateTickets>(){}.getType());
            validateTickets(requestValidateTickets);
        }
    }

    public void validateTickets(RequestValidateTickets requestValidateTickets){
        DataService service = ApiClient.getInstance().create(DataService.class);
        Call<ResponseValidateTickets> call = service.validateTickets(requestValidateTickets);
        call.enqueue(new Callback<ResponseValidateTickets>() {
            @Override
            public void onResponse(Call<ResponseValidateTickets> call, Response<ResponseValidateTickets> response) {
                text.setText(response.body().getMessage());
                if(response.body().getSuccess())
                    text.setBackgroundColor(Color.parseColor("#00cc00"));
                else
                    text.setBackgroundColor(Color.parseColor("#ff0000"));
            }

            @Override
            public void onFailure(Call<ResponseValidateTickets> call, Throwable t) {
                text.setText("Failed validating tickets");
                text.setBackgroundColor(Color.parseColor("#ff0000"));
            }
        });
    }
}
