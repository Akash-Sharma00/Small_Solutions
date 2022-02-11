package com.example.smallsolutions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatWindowAdapter extends RecyclerView.Adapter<ChatWindowAdapter.viewHolder> {
    ArrayList <ChatMessageLoader> messages;
    Message_Window_Activity message_window_activity;

    public ChatWindowAdapter(ArrayList<ChatMessageLoader> messages, Message_Window_Activity message_window_activity) {
        this.messages = messages;
        this.message_window_activity = message_window_activity;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sender_massage_card,parent,false);
        return new ChatWindowAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.SMessage.setText(messages.get(position).getMessage());
        holder.STime.setText(messages.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView RMessage, RTime, SMessage, STime;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            SMessage = itemView.findViewById(R.id.SenderMessage);
            STime = itemView.findViewById(R.id.senderTime);

        }
    }
}
