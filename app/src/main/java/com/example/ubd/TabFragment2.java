package com.example.ubd;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.SYSTEM_HEALTH_SERVICE;

public class TabFragment2 extends Fragment {
    private static final MediaType JSON = MediaType.parse("application/json;charset = uft-8");
    private ConnectServer connectServerPost = new ConnectServer();
    private ConnectServer connectServerPost2 = new ConnectServer();
    private ArrayList<String> imagebitmap;
    private JSONArray jsonArray;
    public  String email;
    public ArrayList<String> imageList;
    public String s;
    public String res;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.tab_fragment_2, container, false);
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        FloatingActionButton fad = (FloatingActionButton) v.findViewById(R.id.fad222);

        fad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromGallery();
            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(),4);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<id_imagesetlist> createLists = new ArrayList<>();
        String email=getActivity().getIntent().getExtras().getString("email");
        String[] projection = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        get("http://192.249.28.109:8000/getimages");
        jsonRead(res);
        System.out.println("sheepshakeit");
        System.out.println(imageList);
        MyAdapter adapter = new MyAdapter(getContext(), imageList);
        recyclerView.setAdapter(adapter);
        return v;
    }

    private void getImageFromGallery() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);

    }
    private void jsonRead(String data){
        imageList = new ArrayList<>();
        System.out.println(data);
        System.out.println("shit");
        try {
            jsonArray = new JSONArray(data);
            System.out.println("fuck");
            System.out.println(jsonArray);
            for (int i =0 ; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String bitmap = jsonObject.getString("bitmap");
                imageList.add(bitmap);
            }
            System.out.println(imageList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
            res=response.body().string();
            System.out.println(res);
        } catch (Exception e){
            System.err.println(e.toString());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("wow");
        try {
            // 선택한 이미지에서 비트맵 생성
            InputStream in =getActivity().getContentResolver().openInputStream(data.getData());
            System.out.println(in);
            Bitmap img = BitmapFactory.decodeStream(in);
            Uri img2=getImageUri(getContext(),img);
            String email=getActivity().getIntent().getExtras().getString("email");
            connectServerPost.requestPost("http://192.249.28.109:8000/photos", email, img2.toString());
            get("http://192.249.28.109:8000/getimages");
            jsonRead(res);
            System.out.println("wow");
            System.out.println(imageList);
            MyAdapter adapter = new MyAdapter(getContext(), imageList);
            View v = getLayoutInflater().inflate(R.layout.tab_fragment_2, null, false);
            RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.imagegallery);
            recyclerView.setAdapter(adapter);
            System.out.println(img2);
        } catch (Exception e) {
            System.out.println("fucking");
            e.printStackTrace();
        }
    }
    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }



    private Uri getUriFromPath(String filePath) {
        long photoId;
        Uri photoUri = MediaStore.Images.Media.getContentUri("external");

        String[] projection = {MediaStore.Images.ImageColumns._ID};
        // TODO This will break if we have no matching item in the MediaStore.
        Cursor cursor = getActivity().getContentResolver().query(photoUri, projection, MediaStore.Images.ImageColumns.DATA + " LIKE ?", new String[] { filePath }, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(projection[0]);
        photoId = cursor.getLong(columnIndex);

        cursor.close();
        return Uri.parse(photoUri.toString() + "/" + photoId);
    }
    class ConnectServer{
        OkHttpClient client = new OkHttpClient();
        public void requestPost(String url, String email, String bitmap){
            System.out.println(email);
            RequestBody requestBody=null;
            if (bitmap==null){
                requestBody = new FormBody.Builder().add("email",email).build();
            }else{
                requestBody = new FormBody.Builder().add("email",email).add("bitmap", bitmap).build();
            }
            System.out.println(requestBody);
            Request request = new Request.Builder().url(url).post(requestBody).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e);
                    System.out.println("sipsaeya!!!!!!!!!!!!");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d("aaaa", "Response Body is " + response.body().string());
                }
            });

        }
        public void getimages(String url, String email){
            System.out.println(url);
            System.out.println(email);
            RequestBody requestBody = new FormBody.Builder().add("email",email).build();
            System.out.println(requestBody);
            Request request = new Request.Builder().url(url).post(requestBody).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e);
                    System.out.println("sipsaeya!!!!!!!!!!!!");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    s=response.body().string();
                    System.out.println("dd");
                    System.out.println(s);
                }
            });

        }

    }

}