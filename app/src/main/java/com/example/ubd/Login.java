package com.example.ubd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class Login extends AppCompatActivity {

    private LoginButton loginButton;

    private CallbackManager callbackManager;

    private String name;
    private String imageurl;
    private String email;


    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        if(AccessToken.getCurrentAccessToken()!=null){
            final ImageButton loggingin = (ImageButton) findViewById(R.id.loginwithfacebook);
            loggingin.setImageResource(R.drawable.loggingin);
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            // Application code
                            System.out.println(response.getJSONObject());
                            try {
                                name=response.getJSONObject().getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                email=response.getJSONObject().getString("email");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                imageurl="http://graph.facebook.com/"+response.getJSONObject().getString("id")+"/picture?width=100&height=100";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            System.out.println(imageurl);
                            startActivity(new Intent(getApplicationContext(),MainActivity.class).putExtra("name",name).putExtra("email",email).putExtra("url",imageurl));
                            finish();
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link,picture,email");
            request.setParameters(parameters);
            request.executeAsync();
        }
        ImageButton loginwithfacebook=findViewById(R.id.loginwithfacebook);
        loginButton=findViewById(R.id.login_button);
        loginwithfacebook.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                loginButton.performClick();
            }
        });
        callbackManager = CallbackManager.Factory.create();

        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                AccessToken accessToken = loginResult.getAccessToken();
                GraphRequest request = GraphRequest.newMeRequest(accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Application code
                                System.out.println(response.getJSONObject());
                                try {
                                    name=response.getJSONObject().getString("name");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    email=response.getJSONObject().getString("email");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    imageurl="http://graph.facebook.com/"+response.getJSONObject().getString("id")+"/picture?width=100&height=100";
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(imageurl);
                                ImageButton loggingin = (ImageButton) findViewById(R.id.loginwithfacebook);
                                loggingin.setImageResource(R.drawable.loggingin);
                                startActivity(new Intent(getApplicationContext(),MainActivity.class).putExtra("name",name).putExtra("email",email).putExtra("url",imageurl));
                                finish();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,picture,email");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    private void getData(JSONObject object) {
        try{
            URL profile_picture = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=250&height=250");
            System.out.println(profile_picture);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
