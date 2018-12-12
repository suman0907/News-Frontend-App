package com.example.sumansinghrajput.newsfeed;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Suman Singh Rajput on 25-04-2018.
 */

public class SearchRecyler extends Fragment implements Callback<GetResponse> {

    RecyclerView serch;
    Context context;
    GetResponse res;
    List<Model> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.searchrecyler, container, false);
        serch = (RecyclerView) view.findViewById(R.id.serch);
        getdata();
        context = getContext();
        return view;
    }


    public void getdata() {
        //   loading = ProgressDialog.show(context,"Data Loading","Please wait .. ");
        String apiendp ="news/headline_query";

        Retrofit retrofit = new Retrofit.Builder()// R hits the link.. and converts the raw to iss type. widout R fit. no converter.
                .baseUrl("https://glacial-meadow-99766.herokuapp.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<GetResponse> call = api.getApires(apiendp,MainActivity.search.getText().toString());
        call.enqueue(this); // call

    }
    @Override

    public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {

        if(response==null) {
            Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
        } else {
            res = response.body();
            list = res.getData();
            showData();
        }

    }

    public void showData() {
        serch.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);// Tells R View ki kaise aayenge.. R/V ko nhi pta.
        llm.setOrientation(LinearLayoutManager.VERTICAL);// Retard R view. wants Lin. Lay Man. and adapter.
        serch.setLayoutManager(llm);
        serch.setAdapter(new SearchAdapter(list,context));
    }
    @Override
    public void onFailure(Call<GetResponse> call, Throwable t) {
        Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();

    }
}
