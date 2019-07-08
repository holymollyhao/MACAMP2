package com.example.ubd;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class id_imagesetlist implements Serializable{

    private String image_title;
    private String Image_Uri;
    private ArrayList<Bitmap> bitmapArrayList;
    public String id;
    public id_imagesetlist(String id, String image_Uri){
        id=id;
        Image_Uri=image_Uri;
    }
    public String getId(){return id;}
    public String getImage_Uri() {
        return Image_Uri;
    }

}






/*
public class Contact implements Serializable {
    private String Name;
    private String Phone;
    private String Image_Uri;

    public Contact(){

    }
    public Contact(String name, String phone, String image_Uri ) {
        Name =name;
        Phone =phone;
        Image_Uri=image_Uri;

    }


    public String getName() {
        return Name;
    }

    public String getNumber() {
        return Phone;
    }
    public String getImage_Uri(){
        return Image_Uri;
    }


    public void setName(String name) {
        this.Name = name;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

}
*/
