package com.tickepaymentsystem.cmov.customerapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tickepaymentsystem.cmov.customerapp.Adapters.ProductAdapter;
import com.tickepaymentsystem.cmov.customerapp.Adapters.ShowAdapter;
import com.tickepaymentsystem.cmov.customerapp.Models.Product;
import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Singleton;

import java.util.ArrayList;
import java.util.List;

public class CafetariaFragment extends Fragment {

    private ProductAdapter adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        getProducts();

        return view;
    }

    private void getProducts() {
        List<Product> products = new ArrayList<>();
        Product popcorn = new Product(2, "Popcorn", 3.0);
        Product sandwich = new Product(4, "Sandwich", 1.0);
        Product coffe = new Product(1, "Coffe", 1.0);
        Product sodaDrink = new Product(3, "Soda drink", 3.0);

        products.add(popcorn);
        products.add(sandwich);
        products.add(coffe);
        products.add(sodaDrink);

        generateDataList(products);
    }

    private void generateDataList(List<Product> products) {
        Singleton.products = products;
        recyclerView = getActivity().findViewById(R.id.list_products);
        adapter = new ProductAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
