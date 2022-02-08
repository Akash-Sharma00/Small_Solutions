package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.DashPathEffect;
import android.os.Bundle;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Message_Window_Activity extends AppCompatActivity {
    CircleImageView Image;
    RecyclerView recycler;
    ArrayList<UserDetails>messageHolder;
    ChatWindowAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_window);

        Image = findViewById(R.id.windowImg);

        messageHolder = new ArrayList<>();
        recycler = findViewById(R.id.message_window);
        LinearLayoutManager  linearLayoutManager =  new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(new ChatWindowAdapter(messageHolder, this));
        chatAdapter = new ChatWindowAdapter(messageHolder, this);
        recycler.setHasFixedSize(true);


        UserDetails user = new UserDetails("4:57 8-02-2022","Hii how are you");
        UserDetails user2 = new UserDetails("4:57 8-02-2022","I");
        messageHolder.add(user);
        messageHolder.add(user);
        messageHolder.add(user);
        messageHolder.add(user);
        messageHolder.add(user);
        messageHolder.add(user);
        messageHolder.add(user);
        messageHolder.add(user);

        messageHolder.add(user2);
        messageHolder.add(user2);
        messageHolder.add(user2);
        messageHolder.add(user2);
        messageHolder.add(user2);

    }
}