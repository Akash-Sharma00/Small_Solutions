package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {


    RecyclerView ChatRecyclerView;
    ArrayList<ChatMessageLoader> ChatHolder;
    ChatRecyclerAdapter chatRecyclerAdapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        ChatHolder = new ArrayList<>();
        ChatRecyclerView = findViewById(R.id.chat_Recycler);
        ChatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerAdapter = new ChatRecyclerAdapter(ChatActivity.this, ChatHolder);
        ChatRecyclerView.setHasFixedSize(true);
        ChatRecyclerView.setAdapter(chatRecyclerAdapter);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users/chats/"+auth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        ChatMessageLoader userDetails = dataSnapshot.getValue(ChatMessageLoader.class);
                        ChatHolder.add(userDetails);
                }
                ChatRecyclerView.setAdapter(new ChatRecyclerAdapter(ChatActivity.this,ChatHolder));
                if(ChatHolder.isEmpty()){
                    findViewById(R.id.chatHistory).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}