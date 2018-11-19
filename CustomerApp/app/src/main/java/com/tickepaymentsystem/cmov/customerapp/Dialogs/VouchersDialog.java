package com.tickepaymentsystem.cmov.customerapp.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tickepaymentsystem.cmov.customerapp.Models.CafetariaOrder;
import com.tickepaymentsystem.cmov.customerapp.Models.Requests.RequestBuyTickets;
import com.tickepaymentsystem.cmov.customerapp.Models.Requests.RequestCafetariaOrder;
import com.tickepaymentsystem.cmov.customerapp.Models.Voucher;
import com.tickepaymentsystem.cmov.customerapp.QRCodeActivity;
import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Singleton;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;
import com.tickepaymentsystem.cmov.customerapp.Utils.Security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class VouchersDialog extends AppCompatDialogFragment {

    TextView coffeeAvailable;
    TextView popcornAvailable;
    TextView discountAvailable;
    TextView coffeeQuantity;
    TextView popcornQuantity;
    TextView discountQuantity;
    Button coffeMinus;
    Button coffeePlus;
    Button popcornMinus;
    Button popcornPlus;
    Button discountMinus;
    Button discountPlus;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_vouchers, null);

        coffeeAvailable = view.findViewById(R.id.voucher_coffee_max_available);
        popcornAvailable = view.findViewById(R.id.voucher_popcorn_max_available);
        discountAvailable = view.findViewById(R.id.voucher_discount_max_available);

        coffeeQuantity = view.findViewById(R.id.voucher_coffee_quantity);
        popcornQuantity = view.findViewById(R.id.voucher_popcorn_quantity);
        discountQuantity = view.findViewById(R.id.voucher_discount_quantity);

        coffeMinus = view.findViewById(R.id.voucher_coffee_minus);
        coffeePlus = view.findViewById(R.id.voucher_coffee_plus);
        popcornMinus = view.findViewById(R.id.voucher_popcorn_minus);
        popcornPlus = view.findViewById(R.id.voucher_popcorn_plus);
        discountMinus = view.findViewById(R.id.voucher_discount_minus);
        discountPlus = view.findViewById(R.id.voucher_discount_plus);

        initLayout();

        builder.setView(view)
                .setTitle("Select vouchers (max: 2)")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(validateVouchers()){
                            onBtnGenerateQRCode();
                        } else {
                            Toasty.info(getContext(), "A maximum of 2 vouchers and 1 of 5% discount are allowed.", Toast.LENGTH_LONG, true).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }

    private void initLayout(){
        for(int i = 0; i < Singleton.orderVouchers.size(); i++){
            Singleton.orderVouchers.get(i).setQuantity(0);
        }

        int discount = Singleton.orderVouchers.get(0).getMaxAvailable() >= 1? 1 : 0;
        int coffee = Singleton.orderVouchers.get(1).getMaxAvailable() >= 2? 2 : 0;
        int popcorn = Singleton.orderVouchers.get(2).getMaxAvailable() >= 2? 2 : 0;

        discountAvailable.setText(Integer.toString(discount));
        coffeeAvailable.setText(Integer.toString(coffee));
        popcornAvailable.setText(Integer.toString(popcorn));

        discountMinus.setOnClickListener((View v) -> onBtnDecreaseAmount(discountQuantity, 0));
        discountPlus.setOnClickListener((View v) -> onBtnIncreaseAmount(discountQuantity, 0));
        coffeMinus.setOnClickListener((View v) -> onBtnDecreaseAmount(coffeeQuantity, 1));
        coffeePlus.setOnClickListener((View v) -> onBtnIncreaseAmount(coffeeQuantity, 1));
        popcornMinus.setOnClickListener((View v) -> onBtnDecreaseAmount(popcornQuantity, 2));
        popcornPlus.setOnClickListener((View v) -> onBtnIncreaseAmount(popcornQuantity, 2));
    }

    private void onBtnIncreaseAmount(TextView view, int position){
        int quantity = 0;

        if(position == 0){
            quantity = 1;
        } else {
            if(Singleton.orderVouchers.get(position).getQuantity() < 2){
                quantity = Singleton.orderVouchers.get(position).getQuantity()+1;
            } else {
                quantity = 2;
            }
        }

        Singleton.orderVouchers.get(position).setQuantity(quantity);
        view.setText(Integer.toString(quantity));
    }

    private void onBtnDecreaseAmount(TextView view, int position){
        int quantity = Singleton.orderVouchers.get(position).getQuantity()-1;
        if(quantity >= 0){
            Singleton.orderVouchers.get(position).setQuantity(quantity);
            view.setText(Integer.toString(quantity));
        }
    }

    private boolean validateVouchers(){
        if(Singleton.orderVouchers.get(0).getQuantity() > 1)
            return false;

        int quantity = 0;

        for(int i = 0; i < Singleton.orderVouchers.size(); i++){
            quantity+=Singleton.orderVouchers.get(i).getQuantity();
        }

        return (quantity <= 2);
    }

    private void onBtnGenerateQRCode() {
        List<Voucher> vouchers = new ArrayList<>();

        Singleton.vouchers.subList(0, 4);

        for(int i = 0; i < Singleton.orderVouchers.size(); i++){
           vouchers.addAll(Singleton.vouchers.subList(0, Singleton.orderVouchers.get(i).getQuantity()));
        }

        Gson gson = new Gson();
        CafetariaOrder message = new CafetariaOrder(Singleton.products, Singleton.userUUID, vouchers);

        try {
            String signedMessage = Security.generateSignedMessage(Singleton.userName, gson.toJson(message).toString());
            RequestCafetariaOrder requestCafetariaOrder = new RequestCafetariaOrder(message, signedMessage);
            String qrcode = gson.toJson(requestCafetariaOrder);

            Intent intent = new Intent(getContext(), QRCodeActivity.class);
            intent.putExtra(Constants.CAFETARIA_ORDER, qrcode);
            getContext().startActivity(intent);
        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException | IOException | KeyStoreException | CertificateException | UnrecoverableEntryException e) {
            e.printStackTrace();
        }
    }
}
