package com.tickepaymentsystem.cmov.customerapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tickepaymentsystem.cmov.customerapp.Adapters.TicketAdapter;
import com.tickepaymentsystem.cmov.customerapp.Models.Ticket;
import com.tickepaymentsystem.cmov.customerapp.R;

import java.util.ArrayList;

public class TicketsFragment extends Fragment {

    private ArrayList<Ticket> tickets = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tickets, container, false);
        ListView lv = view.findViewById(R.id.list_tickets);

        //TODO - Substitute by real data
        generateList();
        lv.setDivider(null);
        lv.setAdapter(new TicketAdapter(getContext(), R.layout.list_item_ticket, tickets));

        return view;
    }

    private void generateList() {
        for(int i = 0; i < 10; i++)
            tickets.add(new Ticket("123e4567-e89b-12d3-a456-426655440000",4, 2, "New show coming", "Dec 21, 2018", "url"));
    }
}
