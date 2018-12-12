package com.example.sumansinghrajput.newsfeed;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements  View.OnClickListener {


    public static FragmentManager frman;
    FragmentTransaction frtran;
    public static Fragment current_frag;

    TopNews frgobj;
    public static EditText search;
    public Button go;
    LinearLayout lin1;
    SearchRecyler frag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search=(EditText) findViewById(R.id.search);
        go=(Button)findViewById(R.id.go);
        go.setOnClickListener(this);



        frgobj = new TopNews();
        frag2= new SearchRecyler();


        lin1 = (LinearLayout) findViewById(R.id.pf1);
        frman = getSupportFragmentManager();
        frtran = frman.beginTransaction();
        if(current_frag == null)
        {
            frtran.replace(R.id.pf1,frgobj);
            frtran.show(frgobj);
            current_frag = frgobj;
        }
        else
        {
            frtran.remove(current_frag);
            frtran.replace(R.id.pf1,frgobj);
            frtran.show(frgobj);
            current_frag = frgobj;
        }
        frtran.commit();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.go)
        {
            if (search != null && !search.getText().toString().equals(""))
            {

                FragmentTransaction fragmentTransaction = frman.beginTransaction();
                frag2 = new SearchRecyler();
                fragmentTransaction.remove(current_frag);
                fragmentTransaction.replace(R.id.pf1,frag2);
                current_frag = frag2;
                fragmentTransaction.show(frag2);
                fragmentTransaction.commit();

            }
            else  {
                Toast.makeText(this, "Enter your search ", Toast.LENGTH_SHORT).show();
                return ;
            }
        }


    }
}
