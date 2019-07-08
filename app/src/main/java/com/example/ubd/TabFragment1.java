package com.example.ubd;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubd.Product;
import com.example.ubd.ProductAdapter;
import com.example.ubd.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TabFragment1 extends Fragment {
    Button button;
    public  String viewdata;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> productList;
    String name;
    String phone;
    String email;
    private JSONArray jsonArray;

    FloatingActionButton fad;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        //Context context = view.getContext();
        Context context = view.getContext();


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        get("http://192.249.28.109:8000/contactlist");
        jsonRead(viewdata);

        fad = (FloatingActionButton) view.findViewById(R.id.fad);




        fad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddContact.class);

                startActivity(intent);

            }
        });



        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProductAdapter(getContext(), productList);
        recyclerView.setAdapter(adapter);

        return view;
    }




    private void jsonRead(String data){
        productList = new ArrayList<>();
        System.out.println(data);
        System.out.println("shit");
        try {
            jsonArray = new JSONArray(data);
            System.out.println("fuck");
            System.out.println(jsonArray);
            for (int i =0 ; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                name = jsonObject.getString("name");
                email = jsonObject.getString("email");
                phone = jsonObject.getString("phone");
                productList.add(new Product(name,email,phone));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        View view = getLayoutInflater().inflate(R.layout.tab_fragment_1, null, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProductAdapter(getContext(), productList);
        recyclerView.setAdapter(adapter);
    }
    public void get(String requestURL) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()

                    .url(requestURL)
                    .build(); //GET Request

            //동기 처리시 execute함수 사용
            Response response = client.newCall(request).execute();
            //출력

            System.out.println("running");
            viewdata = response.body().string();
        } catch (Exception e){
            System.err.println(e.toString());
        }
    }








}