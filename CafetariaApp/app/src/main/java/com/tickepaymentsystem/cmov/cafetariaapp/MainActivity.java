package com.tickepaymentsystem.cmov.cafetariaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
                Toast.makeText(this, "Scan cancelled", Toast.LENGTH_LONG).show();
            } else{
                Log.d("resulta", "aaaa");
                Log.d("result", result.getContents().toString());
                Toast.makeText(this, result.getContents().toString(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
