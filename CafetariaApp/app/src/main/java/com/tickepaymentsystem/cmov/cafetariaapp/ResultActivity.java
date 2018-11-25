package com.tickepaymentsystem.cmov.cafetariaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tickepaymentsystem.cmov.cafetariaapp.Adapters.ProductAdapter;
import com.tickepaymentsystem.cmov.cafetariaapp.Adapters.VoucherAdapter;
import com.tickepaymentsystem.cmov.cafetariaapp.Client.ApiClient;
import com.tickepaymentsystem.cmov.cafetariaapp.Client.DataService;
import com.tickepaymentsystem.cmov.cafetariaapp.Models.Product;
import com.tickepaymentsystem.cmov.cafetariaapp.Models.RequestCafetariaOrder;
import com.tickepaymentsystem.cmov.cafetariaapp.Models.ResponseCafetariaOrder;
import com.tickepaymentsystem.cmov.cafetariaapp.Models.Voucher;
import com.tickepaymentsystem.cmov.cafetariaapp.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    TextView order;
    TextView nif;
    TextView total;
    RecyclerView recyclerViewProducts;
    RecyclerView recyclerViewVouchers;
    private ProductAdapter productAdapter;
    private VoucherAdapter voucherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String qrcodeString = bundle.getString(Constants.VALIDATION);

            Gson gson = new Gson();

            RequestCafetariaOrder requestCafetariaOrder = gson.fromJson(qrcodeString, new TypeToken<RequestCafetariaOrder>(){}.getType());
            validateTickets(requestCafetariaOrder);
        }
    }

    public void validateTickets(RequestCafetariaOrder requestCafetariaOrder){
        DataService service = ApiClient.getInstance().create(DataService.class);
        Call<ResponseCafetariaOrder> call = service.cafetariaOrder(requestCafetariaOrder);
        call.enqueue(new Callback<ResponseCafetariaOrder>() {
            @Override
            public void onResponse(Call<ResponseCafetariaOrder> call, Response<ResponseCafetariaOrder> response) {
                if(response.body().getSuccess()){
                    Toasty.success(getApplicationContext(), "Success validating cafetaria order", Toast.LENGTH_LONG, true).show();

                    order = findViewById(R.id.order);
                    nif = findViewById(R.id.nif);
                    total = findViewById(R.id.total);


                    String orderNumber = "Order nº " + response.body().getOrder().getId();
                    String totalPrice = String.format( "%.2f", response.body().getTotalPrice()) + " €";

                    order.setText(orderNumber);
                    nif.setText(Integer.toString(response.body().getUserNif()));
                    total.setText(totalPrice);

                    generateProductsDataList(response.body().getProducts());
                    generateVouchersDataList(response.body().getVouchers());
                } else {
                    Toasty.error(getApplicationContext(), "Failed validating cafetaria order", Toast.LENGTH_LONG, true).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCafetariaOrder> call, Throwable t) {
                Toasty.error(getApplicationContext(), "Failed validating cafetaria order", Toast.LENGTH_LONG, true).show();
            }
        });
    }

    private void generateProductsDataList(List<Product> products) {
        List<Product> newProducts = new ArrayList<>();

        for (int i = 0; i < products.size(); i++){
            if(products.get(i).getQuantity() > 0)
                newProducts.add(products.get(i));
        }

        recyclerViewProducts = this.findViewById(R.id.list_products);
        productAdapter = new ProductAdapter(newProducts, getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewProducts.setLayoutManager(layoutManager);
        recyclerViewProducts.setAdapter(productAdapter);
    }

    private void generateVouchersDataList(List<Voucher> vouchers) {
        recyclerViewVouchers = this.findViewById(R.id.list_vouchers);
        voucherAdapter = new VoucherAdapter(vouchers, getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewVouchers.setLayoutManager(layoutManager);
        recyclerViewVouchers.setAdapter(voucherAdapter);
    }
}
