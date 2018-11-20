package com.tickepaymentsystem.cmov.customerapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tickepaymentsystem.cmov.customerapp.Client.ApiClient;
import com.tickepaymentsystem.cmov.customerapp.Client.DataService;
import com.tickepaymentsystem.cmov.customerapp.Models.OrderTransaction;
import com.tickepaymentsystem.cmov.customerapp.Models.Responses.ResponseTransactions;
import com.tickepaymentsystem.cmov.customerapp.Models.TicketTransaction;
import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Singleton;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionsFragment extends Fragment {

    private ProgressDialog progressDialog;
    private List<TicketTransaction> ticketTransactions;
    private List<OrderTransaction> orderTransactions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_transactions, container, false);

        getTransactions(view);

        return view;
    }

    public void getTransactions(View view){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(Constants.LOADING);
        progressDialog.show();

        DataService service = ApiClient.getInstance().create(DataService.class);
        Call<ResponseTransactions> call = service.getTransactions(Singleton.userUUID);
        call.enqueue(new Callback<ResponseTransactions>() {
            @Override
            public void onResponse(Call<ResponseTransactions> call, Response<ResponseTransactions> response) {
                progressDialog.dismiss();

                Singleton.tickets = response.body().getValidTickets();
                Singleton.vouchers = response.body().getValidVouchers();
                ticketTransactions = response.body().getTicketTransactions();
                orderTransactions = response.body().getOrderTransactions();

                Gson gson = new Gson();
                String tickets = gson.toJson(Singleton.tickets);
                String vouchers = gson.toJson(Singleton.vouchers);

                SharedPreferences sharedPref = getActivity().getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(Constants.TICKETS, tickets);
                editor.putString(Constants.VOUCHERS, vouchers);
                editor.apply();
            }

            @Override
            public void onFailure(Call<ResponseTransactions> call, Throwable t) {
                progressDialog.dismiss();
                Toasty.error(getContext(), Constants.LOADING_ERROR, Toast.LENGTH_LONG, true).show();
            }
        });
    }
}
