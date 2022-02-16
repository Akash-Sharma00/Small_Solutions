package com.example.smallsolutions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Recycler_Adapter extends RecyclerView.Adapter<Home_Recycler_Adapter.viewHolder> {

    Activity activity;
    ArrayList<UserDetails> Holder;


    public Home_Recycler_Adapter(@NonNull Activity activity, ArrayList<UserDetails> Holder) {
        this.activity = activity;
        this.Holder = Holder;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card,parent,false);
        return new Home_Recycler_Adapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Picasso.get().load(Holder.get(position).getImageURL()).into(holder.homeImg);
        holder.homeName.setText(Holder.get(position).getUserName());
        holder.homeProfession.setText(Holder.get(position).getProfession());
    }

    @Override
    public int getItemCount() {
        return Holder.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView homeImg;
        TextView homeName, homeProfession;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            homeImg = itemView.findViewById(R.id.homeImg);
            homeName = itemView.findViewById(R.id.homeName);
            homeProfession = itemView.findViewById(R.id.homeProfession);

        }

        @Override
        public void onClick(View view) {
                int pos = getAdapterPosition();
                UserDetails userDetails = Holder.get(pos);

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
                view.getContext().startActivity(intent);
        }
    }
}
