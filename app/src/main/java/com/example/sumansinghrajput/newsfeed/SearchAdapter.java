package com.example.sumansinghrajput.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Suman Singh Rajput on 25-04-2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchAdapterBindViewHolder> {


    List<Model> list;
    Context context;
    AppCompatActivity start ;
    public SearchAdapter(List<Model> res,Context c1) {
        list = res;                // 1st
        context = c1;
    }
    @Override
    public SearchAdapter.SearchAdapterBindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)   // 3
                .inflate(R.layout.searchadapter,parent,false);
        return new SearchAdapterBindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.SearchAdapterBindViewHolder holder, int position) {


        if(list.get(position).getTitle()!= null) {
            holder.title.setText("" + list.get(position).getTitle());
        }
        if(list.get(position).getDescription()!=null) {
            holder.description.setText(list.get(position).getDescription());
        }
        if(list.get(position).getImage_url()!=null) {
            Picasso picasso = Picasso.with(context);
            if(list.get(position)!=null) {
                picasso.load(list.get(position).getImage_url())   // 5
                        .centerCrop()
                        .fit()
                        .into(holder.image);
            }
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchAdapterBindViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        ImageView image;// 4
        TextView t4;

        public SearchAdapterBindViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.t2);
            description = (TextView) itemView.findViewById(R.id.t3);
            image = (ImageView) itemView.findViewById(R.id.t1);
            t4 = (TextView) itemView.findViewById(R.id.t4);
            t4.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    String url = list.get(getAdapterPosition()).getUrl();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(browserIntent);


                }
            });

        }
    }
}
