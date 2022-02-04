package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Message_Window_Activity extends AppCompatActivity {
    CircleImageView Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_window);

        Image = findViewById(R.id.windowImg);

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/small-solutions-8d943.appspot.com/o/default%2Fdefault.png?alt=media&token=c334a4e3-2d71-4795-a5e4-a27e54862520").into(Image);
    }
}