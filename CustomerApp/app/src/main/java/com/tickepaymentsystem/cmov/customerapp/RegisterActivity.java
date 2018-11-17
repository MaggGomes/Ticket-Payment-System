package com.tickepaymentsystem.cmov.customerapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tickepaymentsystem.cmov.customerapp.Client.ApiClient;
import com.tickepaymentsystem.cmov.customerapp.Client.DataService;
import com.tickepaymentsystem.cmov.customerapp.Models.CreditCard;
import com.tickepaymentsystem.cmov.customerapp.Models.Responses.ResponseRegister;
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
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity{

    private static final String TAG = RegisterActivity.class.getName();
    private ProgressDialog progressDialog;

    // Date picker
    private TextView ccDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    // Fields
    private EditText editName;
    private EditText editPassword;
    private EditText editConfirmpassword;
    private EditText editNif;
    private EditText editType;
    private EditText editNumber;

    // Variables
    private String name;
    private String keyN;
    private String keyE;
    private String password;
    private String confirmpassword;
    private Integer nif;
    private String type;
    private Integer number;
    private String validity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ccDate = (TextView)findViewById(R.id.register_cc_datevalidity);

        ccDate.setOnClickListener((View v) -> onShowDatePickerDialog());
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = year+"-"+month+"-"+day;
                ccDate.setText(date);
            }
        };

        editName = (EditText)findViewById(R.id.register_name);
        editPassword = (EditText)findViewById(R.id.register_password);
        editConfirmpassword= (EditText)findViewById(R.id.register_confirmpassword);
        editNif = (EditText)findViewById(R.id.register_nif);
        editType = (EditText)findViewById(R.id.register_cc_type);
        editNumber = (EditText)findViewById(R.id.register_cc_number);

        findViewById(R.id.btn_login_secondary).setOnClickListener((View v)->onBtnLoginClick());
        findViewById(R.id.btn_register_main).setOnClickListener((View v)->onBtnRegisterClick());
    }

    private void onShowDatePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Material_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);

        dialog.show();
    }

    private void onBtnLoginClick() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void onBtnRegisterClick() {
        name = editName.getText().toString();
        password = editPassword.getText().toString();
        confirmpassword = editConfirmpassword.getText().toString();
        nif = Integer.parseInt(editNif.getText().toString());
        type = editType.getText().toString();
        number = Integer.parseInt(editNumber.getText().toString());
        validity = ccDate.getText().toString();

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
    }

    public void registerUser(Context context, User body) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(Constants.LOADING);
        progressDialog.show();

        DataService service = ApiClient.getInstance().create(DataService.class);
        Call<ResponseRegister> call = service.register(body);

        call.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                progressDialog.dismiss();

                if(response.isSuccessful()) {
                    Singleton.userName = body.getName();
                    Singleton.userUUID = response.body().getId();
                    startActivity(new Intent(context, HomeActivity.class));
                } else {
                    Toast.makeText(context, "Failed to register. Please try again!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Failed to register. Please try again!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
