package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {


    RecyclerView ChatRecyclerView;
    ArrayList<UserDetails> ChatHolder;
    ChatRecyclerAdapter chatRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ChatHolder = new ArrayList<>();
        ChatRecyclerView = findViewById(R.id.chat_Recycler);
        ChatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChatRecyclerView.setAdapter(new ChatRecyclerAdapter(this,ChatHolder));
        chatRecyclerAdapter = new ChatRecyclerAdapter(this, ChatHolder);
        ChatRecyclerView.setHasFixedSize(true);
        ChatRecyclerView.setAdapter(chatRecyclerAdapter);

        UserDetails userDetails = new UserDetails("Akash","","Carpenter","","https://firebasestorage.googleapis.com/v0/b/small-solutions-8d943.appspot.com/o/default%2Fdefault.png?alt=media&token=c334a4e3-2d71-4795-a5e4-a27e54862520");

        ChatHolder.add(userDetails);
        ChatHolder.add(userDetails);
        ChatHolder.add(userDetails);
        ChatHolder.add(userDetails);
        ChatHolder.add(userDetails);
        ChatHolder.add(userDetails);


    }
}