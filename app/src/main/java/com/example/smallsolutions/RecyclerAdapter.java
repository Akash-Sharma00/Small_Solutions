package com.example.smallsolutions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder>
{
    ArrayList<RecyclerGetterNSetter> dataHolder;

    public RecyclerAdapter(ArrayList<RecyclerGetterNSetter> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_desing,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.dpImg.setImageResource(dataHolder.get(position).getDisplayImg());
        holder.NameText.setText(dataHolder.get(position).getNameText());
        holder.ProfessionText.setText(dataHolder.get(position).getProfessionText());
        holder.StarRating.setRating((float) dataHolder.get(position).getStarRating());
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class viewHolder extends RecyclerView.ViewHolder
    {
        ImageView dpImg;
        TextView NameText, ProfessionText;
        RatingBar StarRating;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            dpImg = itemView.findViewById(R.id.displayImg);
            NameText = itemView.findViewById(R.id.nameText);
            ProfessionText = itemView.findViewById(R.id.professionText);
            StarRating = itemView.findViewById(R.id.starRating);
        }
    }

}
