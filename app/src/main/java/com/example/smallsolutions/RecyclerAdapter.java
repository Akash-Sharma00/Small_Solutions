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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder>
{
    ArrayList<UserDetails> dataHolder;
    AllRandom allrandom;
    public RecyclerAdapter(AllRandom allRandom, ArrayList<UserDetails> dataHolder) {
        this.allrandom = allRandom;
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
//        holder.dpImg.setImageResource(dataHolder.get(position).getDisplayImg());
        holder.NameText.setText(dataHolder.get(position).getUserName());
        holder.ProfessionText.setText(dataHolder.get(position).getProfession());
        Picasso.get().load(dataHolder.get(position).getImageURL()).into(holder.profilePhoto);
//        holder.StarRating.setRating((float) dataHolder.get(position).getStarRating());
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
        CircleImageView profilePhoto;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            dpImg = itemView.findViewById(R.id.displayImg);
            NameText = itemView.findViewById(R.id.nameText);
            ProfessionText = itemView.findViewById(R.id.professionText);
            StarRating = itemView.findViewById(R.id.starRating);
            profilePhoto = itemView.findViewById(R.id.displayImg);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            UserDetails userDetails = dataHolder.get(pos);

            String name = userDetails.getUserName();
            String profession = userDetails.getProfession();
            String exp = userDetails.getExperience();
            String contact = userDetails.getUserPhoneNo();
            String mail = userDetails.getUserEmail();
            String age = userDetails.getAge();
            String profilePhotoURL = userDetails.getImageURL();
            String seekerUid = userDetails.getUid();

            Intent intent = new Intent(itemView.getContext(), profileActivity.class);
            intent.putExtra("Name",name);
            intent.putExtra("Profession", profession);
            intent.putExtra("Exp", exp);
            intent.putExtra("Contact", contact);
            intent.putExtra("Mail", mail);
            intent.putExtra("Age", age);
            intent.putExtra("ProfilePhoto", profilePhotoURL);
            intent.putExtra("uid",seekerUid);
            v.getContext().startActivity(intent);
        }
    }

}
