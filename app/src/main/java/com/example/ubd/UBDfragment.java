package com.example.ubd;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UBDfragment extends Fragment {
    public String Res;
    private ArrayList<MovieList> movieList=new ArrayList<>();
    private RecyclerView recyclerView;
    public UBDfragment(){

    }
/*
    public void get(String requestURL) {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(requestURL)
                    .build();

            //비동기 처리 (enqueue 사용)
            client.newCall(request).enqueue(new Callback() {
                //비동기 처리를 위해 Callback 구현
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("error + Connect Server Error is " + e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.println("Response Body is " + response.body().string());
                    Res=response.body().string();
                }

            });

        } catch (Exception e){
            System.err.println(e.toString());
        }
    }
   */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.ubdlist,container, false);
        recyclerView=(RecyclerView) v.findViewById(R.id.movie_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    .url("http://192.249.28.109:8000/gettop250")
                    .build();
            Response response = client.newCall(request).execute();
            JSONObject resObject= null;
            String jsonData=response.body().string();
            JSONArray Jarray= null;
            try {
                Jarray = new JSONArray(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(Jarray);
            System.out.println(Jarray.length());
            for(int i = 0; i<Jarray.length(); i++) {
                JSONObject tmp = null;
                try {
                    tmp = (JSONObject) Jarray.get(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String title=null;
                String gross=null;
                Number ubd=null;
                String url1=null;
                String url2=null;
                String url3=null;
                String genre=null;
                String runtime=null;
                String tickets=null;
                String director=null;
                try {
                    tickets=(String) tmp.get("tickets");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    title=(String) tmp.get("title");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    gross=(String) tmp.get("gross");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    ubd=(Number) tmp.get("ubd");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    url1=(String) tmp.get("image_url");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    url2=(String) tmp.get("movie_url");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    url3=(String) tmp.get("steal_cut");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    genre=(String) tmp.get("genre");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    runtime=(String) tmp.get("runtime");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    director=(String) tmp.get("director").toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MovieList TMP= new MovieList(title,null,director,gross,ubd,url1, url2,url3,genre, runtime,tickets);
                movieList.add(TMP);
                System.out.println(url2);
            }

        }catch (Exception e){
            System.err.println(e);
        }
        UBDAdapter ubdAdapter=new UBDAdapter(getContext(),movieList);
        recyclerView.setAdapter(ubdAdapter);
        return v;
    }
}
