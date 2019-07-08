package com.example.ubd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private Context mCtx;

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.contact_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        System.out.println(position);
        System.out.println("name"+product.getName());
        System.out.println("email"+product.getEmail());
        System.out.println("phone"+product.getPhone());
        holder.tv_Name.setText(product.getName());
        holder.tv_Email.setText(product.getEmail());
        holder.tv_Phone.setText(product.getPhone());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView tv_Name, tv_Email, tv_Phone;

        public ProductViewHolder(@NonNull View itemView) {

            super(itemView);
            tv_Name=itemView.findViewById(R.id.tv_name);
            tv_Email=itemView.findViewById(R.id.tv_email);
            tv_Phone=itemView.findViewById(R.id.tv_phone);
        }
    }

}