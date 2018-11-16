package com.tickepaymentsystem.cmov.customerapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tickepaymentsystem.cmov.customerapp.Client.ApiClient;
import com.tickepaymentsystem.cmov.customerapp.Client.DataService;
import com.tickepaymentsystem.cmov.customerapp.Models.Message;
import com.tickepaymentsystem.cmov.customerapp.Models.PurchaseTicketsResponse;
import com.tickepaymentsystem.cmov.customerapp.Models.RegisterResponse;
import com.tickepaymentsystem.cmov.customerapp.Models.RequestMessage;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;
import com.tickepaymentsystem.cmov.customerapp.Utils.Security;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPublicKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowActivity extends AppCompatActivity{

    private static final String TAG = ShowActivity.class.getName();
    private ProgressDialog progressDialog;

    // Fields
    private TextView name;
    private TextView description;
    private TextView price;
    private TextView date;
    private ImageView image;
    private Button btnPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            int position = bundle.getInt(Constants.SHOW_DETAILS);

            name = (TextView)findViewById(R.id.show_name);
            description = (TextView)findViewById(R.id.show_description);
            price = (TextView)findViewById(R.id.show_price);
            date = (TextView)findViewById(R.id.show_date);
            image = (ImageView)findViewById(R.id.show_image);
            btnPurchase = (Button) findViewById(R.id.btn_purchase);

            name.setText(Singleton.shows.get(position).getName());
            description.setText(Singleton.shows.get(position).getDescription());
            price.setText(Singleton.shows.get(position).getPrice().toString());
            date.setText(Singleton.shows.get(position).getDate());

            Picasso.get()
                    .load(Singleton.shows.get(position).getUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(image);

            btnPurchase.setOnClickListener((View v) -> onBtnPurchase(position));
        }
    }

    // TODO - Implement
    public void onBtnPurchase(int position){
        Message message = new Message(Singleton.userUUID, Singleton.shows.get(position).getId(), Singleton.shows.get(position).getDate(), 4);

        //Message message = new Message("4cd002fc-437f-41b7-996b-cde77a2f55f0", 1, "2019-11-16 00:00:00 +0000", 4);

        Gson gson = new Gson();

        Log.d(TAG, gson.toJson(message).toString());

        try {
            String signedMessage = Security.generateSignedMessage("nome2", gson.toJson(message).toString());

            Log.d(TAG, signedMessage);
            RequestMessage requestMessage = new RequestMessage(message, signedMessage);

            purchaseTickets(this, requestMessage);

        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException | IOException | KeyStoreException | CertificateException | UnrecoverableEntryException e) {
            e.printStackTrace();
        }
    }

    public void purchaseTickets(Context context, RequestMessage body){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(Constants.LOADING);
        progressDialog.show();

        DataService service = ApiClient.getInstance().create(DataService.class);
        Call<PurchaseTicketsResponse> call = service.buyTickets(body);

        call.enqueue(new Callback<PurchaseTicketsResponse>() {
            @Override
            public void onResponse(Call<PurchaseTicketsResponse> call, Response<PurchaseTicketsResponse> response) {
                progressDialog.dismiss();

                if(response.isSuccessful()) {
                    Log.d(TAG, "Tickets purchased." + response.body().toString());

                } else {
                    Log.d(TAG, "unsuccess");
                }
            }

            @Override
            public void onFailure(Call<PurchaseTicketsResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, "Unable to register user.");
            }
        });
    }
}
