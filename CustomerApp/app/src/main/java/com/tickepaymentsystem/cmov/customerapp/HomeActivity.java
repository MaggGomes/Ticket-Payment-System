package com.tickepaymentsystem.cmov.customerapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tickepaymentsystem.cmov.customerapp.Fragments.CafetariaFragment;
import com.tickepaymentsystem.cmov.customerapp.Fragments.ShowsFragment;
import com.tickepaymentsystem.cmov.customerapp.Fragments.TicketsFragment;
import com.tickepaymentsystem.cmov.customerapp.Fragments.TransactionsFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.home_frag_container,
                new ShowsFragment()).commit();
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
                case R.id.nav_cafetaria:
                    selectedFragment = new CafetariaFragment();
                    break;
                case R.id.nav_transactions:
                    selectedFragment = new TransactionsFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.home_frag_container,
                    selectedFragment).commit();

            return true;
        }
    };
}
