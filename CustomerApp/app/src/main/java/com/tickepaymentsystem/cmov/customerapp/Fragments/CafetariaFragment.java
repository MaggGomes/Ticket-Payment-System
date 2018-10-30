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

import com.tickepaymentsystem.cmov.customerapp.Adapters.CafetariaAdapter;
import com.tickepaymentsystem.cmov.customerapp.R;

import java.util.ArrayList;

public class CafetariaFragment extends Fragment {

    private ArrayList<String> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafetaria, container, false);
        ListView lv = view.findViewById(R.id.list_cafetaria);

        //TODO - Substitute by real data
        generateList();
        lv.setDivider(null);
        lv.setAdapter(new CafetariaAdapter(getContext(), R.layout.list_item_cafetaria, data));

        return view;
    }

    private void generateList() {
        for(int i = 0; i < 10; i++)
            data.add("Row number " + i);
    }
}
