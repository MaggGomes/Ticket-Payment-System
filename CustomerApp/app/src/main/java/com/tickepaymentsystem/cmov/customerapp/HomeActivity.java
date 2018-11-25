package com.tickepaymentsystem.cmov.customerapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tickepaymentsystem.cmov.customerapp.Fragments.CafetariaFragment;
import com.tickepaymentsystem.cmov.customerapp.Fragments.FixturesTabs;
import com.tickepaymentsystem.cmov.customerapp.Fragments.ShowsFragment;
import com.tickepaymentsystem.cmov.customerapp.Fragments.TicketsFragment;
import com.tickepaymentsystem.cmov.customerapp.Fragments.TransactionsFragment;
import com.tickepaymentsystem.cmov.customerapp.Fragments.VouchersFragment;
import com.tickepaymentsystem.cmov.customerapp.Models.OrderVoucher;
import com.tickepaymentsystem.cmov.customerapp.Models.Product;
import com.tickepaymentsystem.cmov.customerapp.Models.Ticket;
import com.tickepaymentsystem.cmov.customerapp.Models.Voucher;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.home_frag_container,
                new ShowsFragment()).commit();
    }

    private void initData(){
        List<Product> products = new ArrayList<>();
        Product popcorn = new Product(2, Constants.POPCORN, 3, 0);
        Product sandwich = new Product(4, Constants.SANDWICH, 1, 0);
        Product coffee = new Product(1, Constants.COFFEE, 1, 0);
        Product sodaDrink = new Product(3, Constants.SODA_DRINK, 3, 0);

        products.add(coffee);
        products.add(popcorn);
        products.add(sodaDrink);
        products.add(sandwich);

        Singleton.products = products;

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_INFO, MODE_PRIVATE);
        Singleton.userName = sharedPreferences.getString(Constants.USER_NAME, "");
        Singleton.userUUID = sharedPreferences.getString(Constants.USER_ID, "");
        String tickets = sharedPreferences.getString(Constants.TICKETS, "");
        String vouchers = sharedPreferences.getString(Constants.VOUCHERS, "");

        Gson gson = new Gson();

        if(tickets.length() > 0){
            Singleton.tickets = gson.fromJson(tickets, new TypeToken<List<Ticket>>(){}.getType());
        } else {
            Singleton.tickets = new ArrayList<>();
        }

        if(vouchers.length() > 0){
            Singleton.vouchers = gson.fromJson(vouchers, new TypeToken<List<Voucher>>(){}.getType());
        } else {
            Singleton.vouchers = new ArrayList<>();
        }

        int discountQuantity = 0;
        int coffeeQuantity = 0;
        int popcornQuantity = 0;

        if(Singleton.vouchers.size() > 0) {
            for (int i = 0; i < Singleton.vouchers.size(); i++) {
                switch (Singleton.vouchers.get(i).getProductId()) {
                    case 0:
                        discountQuantity += 1;
                        break;
                    case 1:
                        coffeeQuantity += 1;
                        break;
                    case 2:
                        popcornQuantity += 1;
                        break;
                }
            }
        }

        OrderVoucher discountVoucher = new OrderVoucher(0, Math.min(1, discountQuantity), 0);
        OrderVoucher coffeeVoucher = new OrderVoucher(1, Math.min(2, coffeeQuantity), 0);
        OrderVoucher popcornVoucher = new OrderVoucher(2, Math.min(2, popcornQuantity), 0);
        Singleton.orderVouchers = new ArrayList<>();
        Singleton.orderVouchers.add(discountVoucher);
        Singleton.orderVouchers.add(coffeeVoucher);
        Singleton.orderVouchers.add(popcornVoucher);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.nav_shows:
                    selectedFragment = new ShowsFragment();
                    break;
                case R.id.nav_tickets:
                    selectedFragment = new TicketsFragment();
                    break;
                case R.id.nav_vouchers:
                    selectedFragment = new VouchersFragment();
                    break;
                case R.id.nav_cafetaria:
                    selectedFragment = new CafetariaFragment();
                    break;
                case R.id.nav_transactions:
                    selectedFragment = new FixturesTabs();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.home_frag_container,
                    selectedFragment).commit();

            return true;
        }
    };
}
