package com.tickepaymentsystem.cmov.customerapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tickepaymentsystem.cmov.customerapp.Adapters.TicketAdapter;
import com.tickepaymentsystem.cmov.customerapp.Models.CafetariaOrder;
import com.tickepaymentsystem.cmov.customerapp.Models.Requests.RequestCafetariaOrder;
import com.tickepaymentsystem.cmov.customerapp.Models.Requests.RequestValidateTickets;
import com.tickepaymentsystem.cmov.customerapp.Models.Ticket;
import com.tickepaymentsystem.cmov.customerapp.Models.ValidateTickets;
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

public class TicketsFragment extends Fragment {

    private List<Ticket> tickets = new ArrayList<>();
    private TicketAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;

        if(Singleton.tickets.size() == 0){
            view = inflater.inflate(R.layout.fragment_no_result, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_tickets, container, false);
            fab = view.findViewById(R.id.list_tickets_fab);
            fab.setOnClickListener((View v) -> onFabClick());

            for(int i = 0; i < Singleton.tickets.size(); i++){
                Singleton.tickets.get(i).setPicked(false);
            }

            generateDataList(view);
        }

        return view;
    }

    private void onFabClick(){
        if(checkTicketsPicked()){
            generateQRCode();
        } else {
            Toasty.error(getContext(), "Maximum 4 tickets from same show", Toast.LENGTH_LONG, true).show();
        }
    }

    private boolean checkTicketsPicked(){

        for(int i = 0; i < Singleton.tickets.size(); i++){
            if(Singleton.tickets.get(i).isPicked()){
                tickets.add(Singleton.tickets.get(i));
            }
        }

        int showId;

        if(tickets.size() > 0) {
            showId = tickets.get(0).getShowId();
        } else {
            return false;
        }

        for(int i = 0; i < tickets.size(); i++){
            if(tickets.get(i).getShowId() != showId){
                return false;
            }
        }

        return (tickets.size() <= 4);
    }


    private void generateQRCode() {
        for(int i = 0; i < Singleton.tickets.size(); i++){
            if(Singleton.tickets.get(i).isPicked()){
                Singleton.tickets.get(i).setUsed(true);
            }
        }

        Gson gson = new Gson();
        String ticketsGson = gson.toJson(Singleton.tickets);

        SharedPreferences sharedPref = getActivity().getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.TICKETS, ticketsGson);
        editor.apply();

        ValidateTickets message = new ValidateTickets(tickets, Singleton.userUUID);

        try {
            String signedMessage = Security.generateSignedMessage(Singleton.userName, gson.toJson(message).toString());
            RequestValidateTickets requestValidateTickets = new RequestValidateTickets(message, signedMessage);
            String qrcode = gson.toJson(requestValidateTickets);

            Intent intent = new Intent(getContext(), QRCodeActivity.class);
            intent.putExtra(Constants.ORDER, qrcode);
            getContext().startActivity(intent);
        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException | IOException | KeyStoreException | CertificateException | UnrecoverableEntryException e) {
            e.printStackTrace();
        }
    }

    private void generateDataList(View view) {
        recyclerView = view.findViewById(R.id.list_tickets);
        adapter = new TicketAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}