package com.example.smallsolutions;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.viewHolder>{

    ArrayList<UserDetails> Holder;
    public CallLogAdapter(ArrayList<UserDetails> holder) {
        this.Holder = holder;
    }

    @NonNull
    @Override
    public CallLogAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_card,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.receiverNameText.setText(Holder.get(position).getUserName());
        holder.receiver_Contact.setText(Holder.get(position).getUserPhoneNo());
        holder.receiverProfessionText.setText(Holder.get(position).getProfession());
        holder.call_time.setText(Holder.get(position).getTime());
//        holder.receiver_ImgUri.setText(Holder.get(position).getImageUri());
    }

    @Override
    public int getItemCount() {
        return Holder.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView call;
        TextView  receiverNameText, receiverProfessionText, receiver_Contact, call_time;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

//            receiver_ImgUri = itemView.findViewById(R.id.receiver_pro3);
            receiverNameText = itemView.findViewById(R.id.receiver_name);
            receiver_Contact = itemView.findViewById(R.id.receiver_contact);
            receiverProfessionText = itemView.findViewById(R.id.receiver_pro);
            call_time = itemView.findViewById(R.id.call_time);
            call = itemView.findViewById(R.id.call_again);

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number = receiver_Contact.getText().toString();
                    String dial = "tel:" + number;
                    itemView.getContext().startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }
            });

        }
    }
}
