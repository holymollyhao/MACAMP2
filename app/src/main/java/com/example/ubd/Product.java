package com.example.ubd;


import android.net.Uri;

public class Product {
    private String name, email, phone;

    public Product(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) { this.email = email;}
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone() {
        return phone;
    }

}
