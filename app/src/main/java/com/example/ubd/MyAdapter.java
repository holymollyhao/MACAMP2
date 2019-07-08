package com.example.ubd;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import java.io.IOException;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> galleryList;
    private Context context;
    Dialog myGallery;

    public MyAdapter(Context context, ArrayList<String> galleryList) {
        this.galleryList = galleryList;
        this.context = context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_layout, viewGroup, false);
        return new ViewHolder(view);
    }

/*
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v=LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        final MyViewHolder vHolder=new MyViewHolder(v);

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_contact);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_contact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView dialog_name_tv=(TextView) myDialog.findViewById(R.id.dialog_name_id);
                TextView dialog_phone_tv=(TextView) myDialog.findViewById(R.id.dialog_phone_id);
                ImageView dialog_contact_img=(ImageView) myDialog.findViewById(R.id.dialog_img);
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialog_phone_tv.setText(mData.get(vHolder.getAdapterPosition()).getNumber());
                if(mData.get(vHolder.getAdapterPosition()).getImage_Uri()!=null) {
                    dialog_contact_img.setImageURI(Uri.parse(mData.get(vHolder.getAdapterPosition()).getImage_Uri()));
                }
                myDialog.show();
            }
        });


        return vHolder;
    }
 */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        System.out.println("sheep");
        System.out.println(galleryList);
        if(galleryList!=null){
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(galleryList.get(i)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(bitmap!=null){
                bitmap=getResizedBitmap(bitmap,200,200);
                viewHolder.img.setImageBitmap(bitmap);
                myGallery = new Dialog(context);
                myGallery.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                viewHolder.img.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myGallery.setContentView(R.layout.gallery_show);
                        ImageView mgallery=(ImageView) myGallery.findViewById(R.id.imagegallery);
                        mgallery.setImageURI(Uri.parse(galleryList.get(i)));
                        myGallery.show();
                    }
                });

            }


        }


    }
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    @Override
    public int getItemCount() {
        if (galleryList==null){
            return 0;
        }
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private LinearLayout item_gallery;
        public ViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.img);

        }
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

}