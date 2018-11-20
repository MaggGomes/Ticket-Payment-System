package com.tickepaymentsystem.cmov.ticketvalidationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tickepaymentsystem.cmov.ticketvalidationapp.Utils.Constants;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private Button btnScanQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScanQRCode = (Button)findViewById(R.id.scan_button);
        btnScanQRCode.setOnClickListener((View v)->onBtnScanQRCode());
    }

    private void onBtnScanQRCode() {
        IntentIntegrator intent = new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        intent.setBeepEnabled(false);
        intent.setPrompt("Scan QRCode");
        intent.setCameraId(0);
        intent.setBarcodeImageEnabled(true);
        intent.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null){
            if(result.getContents() == null){
                Toasty.error(this, "Scan cancelled", Toast.LENGTH_LONG).show();
            } else{
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra(Constants.VALIDATION, result.getContents());
                this.startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
