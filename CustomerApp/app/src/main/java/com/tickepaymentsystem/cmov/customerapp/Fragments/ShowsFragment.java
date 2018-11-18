package com.tickepaymentsystem.cmov.customerapp.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tickepaymentsystem.cmov.customerapp.Adapters.ShowAdapter;
import com.tickepaymentsystem.cmov.customerapp.Client.ApiClient;
import com.tickepaymentsystem.cmov.customerapp.Client.DataService;
import com.tickepaymentsystem.cmov.customerapp.Models.Show;
import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Singleton;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowsFragment extends Fragment {

    private ShowAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shows, container, false);
        getShows(view);

        return view;
    }

    public void getShows(View view){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(Constants.LOADING);
        progressDialog.show();

        DataService service = ApiClient.getInstance().create(DataService.class);
        Call<List<Show>> call = service.getShows();
        call.enqueue(new Callback<List<Show>>() {
            @Override
            public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
                progressDialog.dismiss();
                generateDataList(view, response.body());
            }

            @Override
            public void onFailure(Call<List<Show>> call, Throwable t) {
                progressDialog.dismiss();
                Toasty.error(getContext(), Constants.LOADING_ERROR, Toast.LENGTH_LONG, true).show();
            }
        });
    }

    private void generateDataList(View view, List<Show> shows) {
        Singleton.shows = shows;
        recyclerView = view.findViewById(R.id.list_shows);
        adapter = new ShowAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}