package com.tickepaymentsystem.cmov.customerapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Singleton;

public class VouchersFragment extends Fragment{

    TextView voucherCoffeeQuantity;
    TextView voucherPopcornQuantity;
    TextView voucherDiscountQuantity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vouchers, container, false);

        voucherCoffeeQuantity = (TextView)view.findViewById(R.id.vouchers_coffee_quantity);
        voucherPopcornQuantity = (TextView)view.findViewById(R.id.vouchers_popcorn_quantity);
        voucherDiscountQuantity = (TextView)view.findViewById(R.id.vouchers_discount_quantity);

        if(Singleton.vouchers.size() > 0){
            int coffee = 0;
            int popcorn = 0;
            int discount = 0;

            for(int i = 0; i < Singleton.vouchers.size(); i++){
                switch (Singleton.vouchers.get(i).getProductId()){
                    case 0:
                        discount+=1;
                        break;
                    case 1:
                        coffee+=1;
                        break;
                    case 2:
                        popcorn+=1;
                        break;
                }
            }

            voucherCoffeeQuantity.setText(Integer.toString(coffee));
            voucherPopcornQuantity.setText(Integer.toString(popcorn));
            voucherDiscountQuantity.setText(Integer.toString(discount));
        }

        return view;
    }
}
