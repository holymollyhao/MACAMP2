package com.example.ubd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class MovieDescrp extends AppCompatActivity {
    public String newString;
    public String newString2;
    public String newString3;
    public String runtime;
    public String gross;
    public String genre;
    public TextView Genre;
    public TextView Runtime;
    public String director;
    public LinearLayout mLayout;
    public String tickets;
    public TextView movieTitle;
    public ImageView stealCut;
    public TextView numpeople;
    public TextView ubd;
    public TextView Director;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
                newString2=null;
                newString3=null;
                runtime=null;
                genre=null;
                tickets=null;
            } else {
                newString= getIntent().getExtras().getString("title");
                newString2=getIntent().getExtras().getString("steal_cut");
                newString3=getIntent().getExtras().getString("ubd");
                runtime=getIntent().getExtras().getString("runtime");
                genre=getIntent().getExtras().getString("genre");
                tickets=getIntent().getExtras().getString("tickets");
                gross=getIntent().getExtras().getString("gross");
                director=getIntent().getExtras().getString("director");
                director=director.substring(2,director.length()-2);
                System.out.println(director.length());
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }


        setContentView(R.layout.moviedescrp);
        movieTitle=(TextView) findViewById(R.id.movie_title);
        stealCut=(ImageView) findViewById(R.id.steal_cut);
        ubd=(TextView) findViewById(R.id.ubd);
        numpeople=(TextView) findViewById(R.id.numpeople);
        Director=(TextView) findViewById(R.id.director);
        Genre=(TextView) findViewById(R.id.genre);
        Runtime=(TextView) findViewById(R.id.runtime);
        movieTitle.setText(newString);
        Picasso.get().load(newString2).into(stealCut);
        ubd.setText(newString3+" UBD");
        Genre.setText(genre);
        Runtime.setText(runtime);
        Director.setText(director);
        numpeople.setText(gross);
        if(parseFloat(newString3)>=100){
            mLayout=(LinearLayout) findViewById(R.id.listy);
            View to_add= getLayoutInflater().inflate(R.layout.ubd_achievement3,null,false);
            mLayout.addView(to_add);
        }
        if(parseFloat(newString3)>=50){
            mLayout=(LinearLayout) findViewById(R.id.listy);
            View to_add= getLayoutInflater().inflate(R.layout.ubd_achievement2,null,false);
            mLayout.addView(to_add);
        }
        if(parseFloat(newString3)>=1){
            mLayout=(LinearLayout) findViewById(R.id.listy);
            View to_add= getLayoutInflater().inflate(R.layout.ubd_achievement,null,false);
            mLayout.addView(to_add);
        }
        mLayout=(LinearLayout) findViewById(R.id.listy);
        View to_add= getLayoutInflater().inflate(R.layout.ticketsbutton,null,false);
        Button btm= to_add.findViewById(R.id.tickets);
        System.out.println(btm);
        btm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(tickets));
                startActivity(browserIntent);
            }
        });

        mLayout.addView(to_add);



    }
}
