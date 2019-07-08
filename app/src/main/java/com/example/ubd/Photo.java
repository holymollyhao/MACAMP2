package com.example.ubd;

public class Photo{
    private String imageURI;
    private String id;
    private String thumbnailURI;

    public Photo(String id,String imageURI){
        this.id=id;
        this.imageURI = imageURI;
    }

    public String getImageURI(){
        return imageURI;
    }
    public String getId(){return id;}


}