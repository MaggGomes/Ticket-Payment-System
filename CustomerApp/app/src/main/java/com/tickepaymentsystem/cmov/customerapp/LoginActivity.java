package com.tickepaymentsystem.cmov.customerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    /*RelativeLayout rellay1, rellay2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };*/
    // Add android:visibility="gone" to rellay1 and rellay2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btn_login_main).setOnClickListener((View v)->onBtnLoginClick());
        findViewById(R.id.btn_register_secondary).setOnClickListener((View v)->onBtnRegisterClick());
        /*rellay1 = (RelativeLayout)findViewById(R.id.rellay1);
        rellay1 = (RelativeLayout)findViewById(R.id.rellay2);
        handler.postDelayed(runnable, 2000);*/
    }

    private void onBtnLoginClick() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    private void onBtnRegisterClick() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}

