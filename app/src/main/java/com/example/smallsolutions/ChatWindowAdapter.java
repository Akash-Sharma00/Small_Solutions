package com.example.smallsolutions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatWindowAdapter extends RecyclerView.Adapter {
    ArrayList <ChatMessageLoader> messages;
    Message_Window_Activity message_window_activity;
    int ITEM_SEND = 1;
    int ITEM_RECEIVE = 2;

    public ChatWindowAdapter(ArrayList<ChatMessageLoader> messages, Message_Window_Activity message_window_activity) {
        this.messages = messages;
        this.message_window_activity = message_window_activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SEND){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sender_massage_card,parent,false);
            return new SenderViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receiver_massage_card,parent,false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessageLoader chatMessageLoader = messages.get(position);

        if (holder.getClass() == SenderViewHolder.class){
            SenderViewHolder viewHolder = (SenderViewHolder) holder;
            viewHolder.SMessage.setText(chatMessageLoader.getMessage());
            viewHolder.STime.setText(chatMessageLoader.getTime());
        }
        else {
            ReceiverViewHolder viewHolder = (ReceiverViewHolder) holder;
            viewHolder.RTime.setText(chatMessageLoader.getTime());
            viewHolder.RMessage.setText(chatMessageLoader.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessageLoader chatMessageLoader = messages.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(chatMessageLoader.getSenderId())){
            return ITEM_SEND;
        }
        else {
            return ITEM_RECEIVE;
        }
    }

    class SenderViewHolder extends RecyclerView.ViewHolder{

        TextView SMessage, STime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            SMessage = itemView.findViewById(R.id.SenderMessage);
            STime = itemView.findViewById(R.id.senderTime);
        }
    }

    class ReceiverViewHolder extends RecyclerView.ViewHolder{
        TextView RMessage, RTime;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            RMessage = itemView.findViewById(R.id.chatMessage);
            RTime = itemView.findViewById(R.id.chatTime);
        }
    }
    
}
