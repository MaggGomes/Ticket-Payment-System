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

import com.tickepaymentsystem.cmov.customerapp.Adapters.ProductAdapter;
import com.tickepaymentsystem.cmov.customerapp.Adapters.ShowAdapter;
import com.tickepaymentsystem.cmov.customerapp.Models.Product;
import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Singleton;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class CafetariaFragment extends Fragment {

    private ProductAdapter adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        getProducts(view);

        return view;
    }

    private void getProducts(View view) {
        List<Product> products = new ArrayList<>();
        Product popcorn = new Product(2, Constants.POPCORN, 3.0);
        Product sandwich = new Product(4, Constants.SANDWICH, 1.0);
        Product coffee = new Product(1, Constants.COFFEE, 1.0);
        Product sodaDrink = new Product(3, Constants.SODA_DRINK, 3.0);

        products.add(popcorn);
        products.add(sandwich);
        products.add(coffee);
        products.add(sodaDrink);

        generateDataList(view, products);
    }

    private void generateDataList(View view, List<Product> products) {
        Singleton.products = products;
        recyclerView = view.findViewById(R.id.list_products);
        adapter = new ProductAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
