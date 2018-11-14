package com.tickepaymentsystem.cmov.customerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;

public class ShowActivity extends AppCompatActivity{

    private TextView name;
    private TextView description;
    private TextView price;
    private TextView date;
    private ImageView image;
    private Button btnPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            int position = bundle.getInt(Constants.SHOW_DETAILS);

            name = (TextView)findViewById(R.id.show_name);
            description = (TextView)findViewById(R.id.show_description);
            price = (TextView)findViewById(R.id.show_price);
            date = (TextView)findViewById(R.id.show_date);
            image = (ImageView)findViewById(R.id.show_image);
            btnPurchase = (Button) findViewById(R.id.btn_purchase);

            name.setText(Singleton.shows.get(position).getName());
            description.setText(Singleton.shows.get(position).getDescription());
            price.setText(Singleton.shows.get(position).getName());
            date.setText(Singleton.shows.get(position).getPrice().toString());

            Picasso.get()
                    .load(Singleton.shows.get(position).getUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(image);

            btnPurchase.setOnClickListener((View v) -> onBtnPurchase());
        }
    }

    // TODO - Implement
    public void onBtnPurchase(){

    }
}
