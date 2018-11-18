package com.tickepaymentsystem.cmov.customerapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tickepaymentsystem.cmov.customerapp.Adapters.ProductAdapter;
import com.tickepaymentsystem.cmov.customerapp.Adapters.ShowAdapter;
import com.tickepaymentsystem.cmov.customerapp.Models.Product;
import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Singleton;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class CafetariaFragment extends Fragment {

    TextView coffePrice;
    TextView popcornPrice;
    TextView sodaDrinkPrice;
    TextView sandwichPrice;
    TextView coffeQuantity;
    TextView popcornQuantity;
    TextView sodadrinkQuantity;
    TextView sandwichQuantity;
    Button coffeMinus;
    Button coffePlus;
    Button popcornMinus;
    Button popcornPlus;
    Button sodadrinkMinus;
    Button sodadrinkPlus;
    Button sandwichMinus;
    Button sandwichPlus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        coffePrice = view.findViewById(R.id.coffe_price);
        popcornPrice = view.findViewById(R.id.popcorn_price);
        sodaDrinkPrice = view.findViewById(R.id.sodadrink_price);
        sandwichPrice = view.findViewById(R.id.sandwich_price);

        coffeQuantity = view.findViewById(R.id.coffe_quantity);
        popcornQuantity = view.findViewById(R.id.popcorn_quantity);
        sodadrinkQuantity = view.findViewById(R.id.sodadrink_quantity);
        sandwichQuantity = view.findViewById(R.id.sandwich_quantity);

        coffeMinus = view.findViewById(R.id.coffee_minus);
        coffePlus = view.findViewById(R.id.coffe_plus);
        popcornMinus = view.findViewById(R.id.popcorn_minus);
        popcornPlus = view.findViewById(R.id.popcorn_plus);
        sodadrinkMinus = view.findViewById(R.id.sodadrink_minus);
        sodadrinkPlus = view.findViewById(R.id.sodadrink_plus);
        sandwichMinus = view.findViewById(R.id.sandwich_minus);
        sandwichPlus = view.findViewById(R.id.sandwich_plus);

        initLayout();

        return view;
    }

    private void initLayout(){
        String price = Singleton.products.get(0).getPrice()+" €";
        coffePrice.setText(price);
        price = Singleton.products.get(1).getPrice()+" €";
        popcornPrice.setText(price);
        price = Singleton.products.get(2).getPrice()+" €";
        sodaDrinkPrice.setText(price);
        price = Singleton.products.get(3).getPrice()+" €";
        sandwichPrice.setText(price);

        coffeMinus.setOnClickListener((View v) -> onBtnDecreaseAmount(coffeQuantity, 0));
        popcornMinus.setOnClickListener((View v) -> onBtnDecreaseAmount(popcornQuantity, 1));
        sodadrinkMinus.setOnClickListener((View v) -> onBtnDecreaseAmount(sodadrinkQuantity, 2));
        sandwichMinus.setOnClickListener((View v) -> onBtnDecreaseAmount(sandwichQuantity, 3));

        coffePlus.setOnClickListener((View v) -> onBtnIncreaseAmount(coffeQuantity, 0));
        popcornPlus.setOnClickListener((View v) -> onBtnIncreaseAmount(popcornQuantity, 1));
        sodadrinkPlus.setOnClickListener((View v) -> onBtnIncreaseAmount(sodadrinkQuantity, 2));
        sandwichPlus.setOnClickListener((View v) -> onBtnIncreaseAmount(sandwichQuantity, 3));
    }

    private void onBtnIncreaseAmount(TextView view, int position){
        int quantity = Singleton.products.get(position).getQuantity()+1;
        Singleton.products.get(position).setQuantity(Singleton.products.get(position).getQuantity()+1);
        view.setText(Integer.toString(quantity));
    }

    private void onBtnDecreaseAmount(TextView view, int position){
        int quantity = Singleton.products.get(position).getQuantity()-1;
        if(quantity >= 0){
            Singleton.products.get(position).setQuantity(quantity);
            view.setText(Integer.toString(quantity));
        }
    }
}
