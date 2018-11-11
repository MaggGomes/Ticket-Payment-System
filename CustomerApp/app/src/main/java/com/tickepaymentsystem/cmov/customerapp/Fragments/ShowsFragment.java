package com.tickepaymentsystem.cmov.customerapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tickepaymentsystem.cmov.customerapp.Adapters.ShowAdapter;
import com.tickepaymentsystem.cmov.customerapp.Models.Show;
import com.tickepaymentsystem.cmov.customerapp.R;

import java.util.ArrayList;

public class ShowsFragment extends Fragment {

    private ArrayList<Show> shows = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shows, container, false);
        ListView lv = view.findViewById(R.id.list_shows);

        //TODO - Substitute by real data
        generateList();
        lv.setDivider(null);
        lv.setAdapter(new ShowAdapter(getContext(), R.layout.list_item_show, shows));

        return view;
    }

    private void generateList() {
        for(int i = 0; i < 10; i++)
            shows.add(new Show("1","New show", "New show coming", "Dec 21, 2018", 20, "url"));
    }
}
