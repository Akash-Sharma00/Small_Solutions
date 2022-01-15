package com.example.smallsolutions;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder>
{
    ArrayList<RecyclerGetterNSetter> dataHolder;

    public RecyclerAdapter(AllRandom allRandom, ArrayList<RecyclerGetterNSetter> dataHolder) {
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

    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView dpImg;
        TextView NameText, ProfessionText;
        RatingBar StarRating;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            dpImg = itemView.findViewById(R.id.displayImg);
            NameText = itemView.findViewById(R.id.nameText);
            ProfessionText = itemView.findViewById(R.id.professionText);
            StarRating = itemView.findViewById(R.id.starRating);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            RecyclerGetterNSetter recyclerGetterNSetter = dataHolder.get(pos);

            String name = recyclerGetterNSetter.getNameText();
            String profession = recyclerGetterNSetter.getProfessionText();
            Double starRatting = recyclerGetterNSetter.getStarRating();

            Toast.makeText(itemView.getContext(), "I am active" + pos + recyclerGetterNSetter, Toast.LENGTH_SHORT).show();
//            v.getContext().startActivity(new Intent(v.getContext(), profileActivity.class));
//            Intent intent = new Intent(itemView.getContext(), profileActivity.class);
//            intent.putExtra("Name",name);
//            intent.putExtra("Profession", profession);
//            intent.putExtra("StarRatting",starRatting);
//            v.getContext().startActivity(intent);
        }
    }

}
