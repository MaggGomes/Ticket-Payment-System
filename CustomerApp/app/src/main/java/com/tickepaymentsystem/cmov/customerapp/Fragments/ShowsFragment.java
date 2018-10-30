package com.tickepaymentsystem.cmov.customerapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tickepaymentsystem.cmov.customerapp.R;

public class ShowsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_shows, container, false);

        String[] names = {"1 show", "2 show", "3 show", "4 show", "2 show", "3 show", "4 show"};
        int[] prices = {1, 2, 3, 4, 5, 6, 7};

        ListView listView = view.findViewById(R.id.list_shows_last);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                names
        );

        listView.setAdapter(arrayAdapter);

        return view;
    }
}
