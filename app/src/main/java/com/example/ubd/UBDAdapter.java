package com.example.ubd;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class UBDAdapter extends RecyclerView.Adapter<UBDAdapter.ViewHolder> {
    Context context;
    List<MovieList> MovieList;

    public UBDAdapter(Context context, List<MovieList> MovieList) {
        this.context = context;
        this.MovieList = MovieList;
    }

    @Override
    public UBDAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ubdlistelement, viewGroup, false);
        ViewHolder vHoler=new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.gross.setText(MovieList.get(i).getGross());
        viewHolder.title.setText(MovieList.get(i).getTitle());
        viewHolder.ubd.setText(MovieList.get(i).getUBD().toString());
        Picasso.get().load(MovieList.get(i).getImage_URL()).into(viewHolder.moviePoster);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                System.out.println(i);
                System.out.println(MovieList.get(i).getTitle());
                context.startActivity(new Intent(context,MovieDescrp.class).putExtra("tickets",MovieList.get(i).getTickets()).putExtra("director",MovieList.get(i).getDirector().toString()).putExtra("gross",MovieList.get(i).getGross()).putExtra("ubd",MovieList.get(i).getUBD().toString()).putExtra("genre",MovieList.get(i).getGenre()).putExtra("runtime",MovieList.get(i).getRuntime()).putExtra("steal_cut",MovieList.get(i).getSteal_Cut()).putExtra("title",MovieList.get(i).getTitle()));
            }
        });
    }
    public int getItemCount(){return MovieList.size();};

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView ubd;
        private TextView gross;
        private ImageView moviePoster;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            ubd = (TextView) itemView.findViewById(R.id.ubd);
            gross = (TextView) itemView.findViewById(R.id.gross);
            moviePoster=(ImageView) itemView.findViewById(R.id.movieposter);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.contact_item);
        }
    }
}