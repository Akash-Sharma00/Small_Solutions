package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class AllRandom extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<RecyclerGetterNSetter> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_random);

        cardList = new ArrayList<>();

        cardList.add(new RecyclerGetterNSetter(R.drawable.mrplumber, 4.4, "Akash", "Plumber"));
        cardList.add(new RecyclerGetterNSetter(R.drawable.mrplumber, 1.4, "Nitin", "Cleaner"));
        cardList.add(new RecyclerGetterNSetter(R.drawable.mrplumber, 4.9, "XYZ", "Developer"));
        cardList.add(new RecyclerGetterNSetter(R.drawable.mrplumber, 5.0, "Aadersh", "Nalla"));

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerAdapter(cardList));

    }

    @Override
    public void onBackPressed() {
        Intent home = new Intent(this,HomeActivity.class);
        startActivity(home);
        super.onBackPressed();
    }
}