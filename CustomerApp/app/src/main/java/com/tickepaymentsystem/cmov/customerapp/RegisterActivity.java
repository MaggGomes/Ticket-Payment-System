package com.tickepaymentsystem.cmov.customerapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.tickepaymentsystem.cmov.customerapp.Client.ApiClient;
import com.tickepaymentsystem.cmov.customerapp.Client.DataService;
import com.tickepaymentsystem.cmov.customerapp.Models.CreditCard;
import com.tickepaymentsystem.cmov.customerapp.Models.Show;
import com.tickepaymentsystem.cmov.customerapp.Models.User;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;
import com.tickepaymentsystem.cmov.customerapp.Utils.Security;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity{

    private static final String TAG = RegisterActivity.class.getName();
    private ProgressDialog progressDialog;

    // Fields
    private EditText editName;
    private EditText editPassword;
    private EditText editConfirmpassword;
    private EditText editNif;
    private EditText editType;
    private EditText editNumber;
    private EditText editValidity;


    // Variables
    private String name;
    private String keyN;
    private String keyE;
    private String password;
    private String confirmpassword;
    private long nif;
    private String type;
    private String number;
    private String validity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = (EditText)findViewById(R.id.register_name);
        editPassword = (EditText)findViewById(R.id.register_password);
        editConfirmpassword= (EditText)findViewById(R.id.register_confirmpassword);
        editNif = (EditText)findViewById(R.id.register_nif);
        editType = (EditText)findViewById(R.id.register_cc_type);
        editNumber = (EditText)findViewById(R.id.register_cc_number);
        editValidity = (EditText)findViewById(R.id.register_cc_validity);


        findViewById(R.id.btn_login_secondary).setOnClickListener((View v)->onBtnLoginClick());
        findViewById(R.id.btn_register_main).setOnClickListener((View v)->onBtnRegisterClick());
    }

    private void onBtnLoginClick() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void onBtnRegisterClick() {
        name = editName.getText().toString();
        password = editPassword.getText().toString();
        confirmpassword = editConfirmpassword.getText().toString();
        nif = Long.parseLong(editNif.getText().toString());
        type = editType.getText().toString();
        number = editNumber.getText().toString();
        validity = editValidity.getText().toString();

        try {
            RSAPublicKey publicKey = (RSAPublicKey) Security.generateRSAKeypair(this, name);
            keyN = publicKey.getModulus().toString(16);
            keyE = publicKey.getPublicExponent().toString(16);

        } catch (NoSuchProviderException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | IOException | KeyStoreException | CertificateException | UnrecoverableEntryException e) {
            e.printStackTrace();
        }

        CreditCard cc = new CreditCard(type, number, validity);
        User user = new User(name, keyN, keyE, password, nif, cc);

        registerUser(this, user);

        /*Gson gson = new Gson();
        String jsonInString = gson.toJson(user);


        Log.d(TAG, keyE);
        Log.d(TAG, keyN);
        Log.d(TAG, jsonInString);*/
    }

    public void registerUser(Context context, User body) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(Constants.LOADING);
        progressDialog.show();

        DataService service = ApiClient.getInstance().create(DataService.class);
        Call<String> call = service.register(body);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressDialog.dismiss();

                if(response.isSuccessful()) {
                    Log.d(TAG, "user registered." + response.body().toString());
                    startActivity(new Intent(context, HomeActivity.class));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG, "Unable to register user.");
            }
        });
    }
}
