package com.tickepaymentsystem.cmov.customerapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tickepaymentsystem.cmov.customerapp.Client.ApiClient;
import com.tickepaymentsystem.cmov.customerapp.Client.DataService;
import com.tickepaymentsystem.cmov.customerapp.Models.Message;
import com.tickepaymentsystem.cmov.customerapp.Models.Responses.ResponseBuyTickets;
import com.tickepaymentsystem.cmov.customerapp.Models.Requests.RequestBuyTickets;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;
import com.tickepaymentsystem.cmov.customerapp.Utils.Security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowActivity extends AppCompatActivity{

    private static final String TAG = ShowActivity.class.getName();
    private int ticketsQuantity = 1;
    private ProgressDialog progressDialog;

    // Fields
    private TextView name;
    private TextView description;
    private TextView price;
    private TextView date;
    private TextView quantity;
    private ImageView image;
    private Button btnPurchase;
    private Button btnIncrease;
    private Button btnDecrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            int position = bundle.getInt(Constants.SHOW_DETAILS);
            String showPrice = Singleton.shows.get(position).getPrice().toString()+" €";

            name = (TextView)findViewById(R.id.show_name);
            description = (TextView)findViewById(R.id.show_description);
            price = (TextView)findViewById(R.id.show_price);
            date = (TextView)findViewById(R.id.show_date);
            quantity = (TextView)findViewById(R.id.show_quantity);
            image = (ImageView)findViewById(R.id.show_image);
            btnPurchase = (Button)findViewById(R.id.btn_purchase);
            btnIncrease = (Button)findViewById(R.id.show_quantity_btn_plus);
            btnDecrease = (Button)findViewById(R.id.show_quantity_btn_minus);

            name.setText(Singleton.shows.get(position).getName());
            description.setText(Singleton.shows.get(position).getDescription());
            price.setText(showPrice);
            quantity.setText(Integer.toString(ticketsQuantity));
            date.setText(Singleton.shows.get(position).getDate());

            Picasso.get()
                    .load(Singleton.shows.get(position).getUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(image);

            btnPurchase.setOnClickListener((View v) -> onBtnPurchase(position));
            btnIncrease.setOnClickListener((View v) -> onBtnIncrease());
            btnDecrease.setOnClickListener((View v) -> onBtnDecrease());
        }
    }

    public void onBtnIncrease(){
        ticketsQuantity+= 1;
        quantity.setText(Integer.toString(ticketsQuantity));
    }

    public void onBtnDecrease(){
        ticketsQuantity-= 1;

        if(ticketsQuantity >= 0){
            quantity.setText(Integer.toString(ticketsQuantity));
        } else {
            ticketsQuantity = 0;
        }
    }

    public void onBtnPurchase(int position){
        if(ticketsQuantity < 1){
            Toasty.info(getApplicationContext(), Constants.LOW_NUMBER_TICKETS, Toast.LENGTH_SHORT, true).show();
            return;
        }

        Message message = new Message(Singleton.userUUID, Singleton.shows.get(position).getId(), Singleton.shows.get(position).getDate(), ticketsQuantity);
        Gson gson = new Gson();

        try {
            String signedMessage = Security.generateSignedMessage(Singleton.userName, gson.toJson(message).toString());
            RequestBuyTickets requestMessage = new RequestBuyTickets(message, signedMessage);
            purchaseTickets(this, requestMessage);

        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException | IOException | KeyStoreException | CertificateException | UnrecoverableEntryException e) {
            e.printStackTrace();
        }
    }

    public void purchaseTickets(Context context, RequestBuyTickets body){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(Constants.LOADING);
        progressDialog.show();

        DataService service = ApiClient.getInstance().create(DataService.class);
        Call<ResponseBuyTickets> call = service.buyTickets(body);

        call.enqueue(new Callback<ResponseBuyTickets>() {
            @Override
            public void onResponse(Call<ResponseBuyTickets> call, Response<ResponseBuyTickets> response) {
                progressDialog.dismiss();

                if(response.isSuccessful()) {
                    Singleton.tickets.addAll(response.body().getTickets());
                    Singleton.vouchers.addAll(response.body().getVouchers());

                    Gson gson = new Gson();
                    String tickets = gson.toJson(Singleton.tickets);
                    String vouchers = gson.toJson(Singleton.vouchers);

                    SharedPreferences sharedPref = getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(Constants.TICKETS, tickets);
                    editor.putString(Constants.VOUCHERS, vouchers);
                    editor.apply();

                    Toasty.success(context, Constants.BUY_TICKETS_SUCCESS, Toast.LENGTH_LONG, true).show();
                } else {
                    Toasty.error(context, Constants.BUY_TICKETS_FAILURE, Toast.LENGTH_LONG, true).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBuyTickets> call, Throwable t) {
                progressDialog.dismiss();
                Toasty.error(context, Constants.BUY_TICKETS_FAILURE, Toast.LENGTH_LONG, true).show();
            }
        });
    }
}
