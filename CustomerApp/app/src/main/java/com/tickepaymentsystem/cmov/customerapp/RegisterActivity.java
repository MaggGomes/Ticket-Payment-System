package com.tickepaymentsystem.cmov.customerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RegisterActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.btn_login_secondary).setOnClickListener((View v)->onBtnLoginClick());
        findViewById(R.id.btn_register_main).setOnClickListener((View v)->onBtnRegisterClick());
    }

    private void onBtnLoginClick() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void onBtnRegisterClick() {
        startActivity(new Intent(this, HomeActivity.class));
    }
}
