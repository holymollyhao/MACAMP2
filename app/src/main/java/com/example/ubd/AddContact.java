package com.example.ubd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddContact extends AppCompatActivity {
    public static final String connecturl = "http://192.249.28.109:8000/contactlist";

    EditText inputname, inputemail, inputphone;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        inputname = (EditText) findViewById(R.id.inputname);
        inputemail = (EditText) findViewById(R.id.inputemail);
        inputphone = (EditText) findViewById(R.id.inputnumber);
        button = (Button) findViewById(R.id.addbutton);


        final AddContact.ConnectServer connectServerPost = new AddContact.ConnectServer();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("M F");
                loginuser(inputname.getText().toString(), inputemail.getText().toString(), inputphone.getText().toString());  ///여기가 문제
                System.out.println("molip");
                System.out.println(inputname.getText().toString());


                connectServerPost.requestPost(connecturl,inputname.getText().toString(),inputemail.getText().toString(),inputphone.getText().toString());

                System.out.println("Molipe2");
                System.out.println(inputname.getText().toString());
                ClearEt(inputname);
                ClearEt(inputemail);
                ClearEt(inputphone);
            }
        });

    }

    public void ClearEt(View view){
        int id = view.getId();
        EditText et = (EditText) view.findViewById(id);
        et.setText("");
    }

    private void loginuser(String name, String email, String phone) {
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"no empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"no empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"no empty",Toast.LENGTH_SHORT).show();
            return;
        }
    }

    class ConnectServer{
        OkHttpClient client = new OkHttpClient();
        public void requestPost(String url, String name, String email, String phone){
            System.out.println("bloody");
            System.out.println(name);
            System.out.println(email);
            RequestBody requestBody = new FormBody.Builder().add("name", name).add("email",email).add("phone", phone).build();
            System.out.println("kaist");
            System.out.println(((FormBody) requestBody).size());
            Request request = new Request.Builder().url(url).post(requestBody).build();
            System.out.println("sibalnoma!!!!!!!!!!!!");
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e);
                    System.out.println("sipsaeya!!!!!!!!!!!!");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.println("yukgyuwau");
                    Log.d("aaaa", "Response Body is " + response.body().string());
                }
            });

        }

    }

}
