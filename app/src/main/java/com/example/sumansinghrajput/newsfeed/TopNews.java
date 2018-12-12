package com.example.sumansinghrajput.newsfeed;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Suman Singh Rajput on 21-04-2018.
 */

public class TopNews  extends Fragment implements AdapterView.OnItemSelectedListener , View.OnClickListener, Callback<CurrentGetResponse> {

    Context n1;
    LayoutInflater layi;
    Spinner spin;
    String[] country = { "in", "us", "jp", "ru","nz", "ca","gb","mx"  };
    String select;

    CurrentGetResponse res;
    List<TopNewsModel> list1;
    RecyclerView tpn;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.topnews,container,false);
        n1=inflater.getContext();
        layi= inflater;
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spin = (Spinner)  view.findViewById(R.id.spinner1);
        spin.setOnItemSelectedListener(this);
        tpn = (RecyclerView) view.findViewById(R.id.tpn);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
       // getdata();
        return view;
    }

    public void getdata() {
        //   loading = ProgressDialog.show(context,"Data Loading","Please wait .. ");
        String apiendp ="news/top_headlines";

        Retrofit retrofit = new Retrofit.Builder()// R hits the link.. and converts the raw to iss type. widout R fit. no converter.
                .baseUrl("https://glacial-meadow-99766.herokuapp.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        TopNewsAPI api = retrofit.create(TopNewsAPI.class);
        Call<CurrentGetResponse> call = api.getApires(apiendp,select);
        call.enqueue(this); // call

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        select = parent.getItemAtPosition(position).toString();
        if(select!=null)
        getdata();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(n1,"Select the country for news",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(Call<CurrentGetResponse> call, retrofit2.Response<CurrentGetResponse> response) {


        if(response==null) {
            Toast.makeText(n1,"Something went wrong",Toast.LENGTH_SHORT).show();
        } else {
            res = response.body();
            list1 = res.getData();
            showData();
        }
    }


    public void showData() {
        tpn.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(n1);// Tells R View ki kaise aayenge.. R/V ko nhi pta.
        llm.setOrientation(LinearLayoutManager.VERTICAL);// Retard R view. wants Lin. Lay Man. and adapter.
        tpn.setLayoutManager(llm);
        tpn.setAdapter(new TopNewsAdapter(list1,n1));
    }

    @Override
    public void onFailure(Call<CurrentGetResponse> call, Throwable t) {
        Toast.makeText(n1,"Something went wrong",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {

    }
}
