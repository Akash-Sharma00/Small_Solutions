package com.example.smallsolutions;

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

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.viewHolder> {


    ChatActivity chatActivity;
    ArrayList<UserDetails> ChatHolder;

    public ChatRecyclerAdapter(ChatActivity chatActivity, ArrayList<UserDetails> chatHolder) {
        this.chatActivity = chatActivity;
        this.ChatHolder = chatHolder;
    }


    @NonNull
    @Override
    public ChatRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_recycler_card,parent,false);
        return new ChatRecyclerAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRecyclerAdapter.viewHolder holder, int position) {

        holder.chatName.setText(ChatHolder.get(position).getUserName());
        holder.chatProfession.setText(ChatHolder.get(position).getProfession());
        Picasso.get().load(ChatHolder.get(position).getImageURL()).into(holder.chatImg);

    }

    @Override
    public int getItemCount() {
        return ChatHolder.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView chatImg;
        TextView chatName, chatProfession;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            chatImg = itemView.findViewById(R.id.chatCardImg);
            chatName = itemView.findViewById(R.id.chatCardName);
            chatProfession = itemView.findViewById(R.id.chatCardProfession);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(itemView.getContext(), "It is working", Toast.LENGTH_SHORT).show();
        }
    }
}
