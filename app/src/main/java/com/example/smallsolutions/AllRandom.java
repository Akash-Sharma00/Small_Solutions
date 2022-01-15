package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllRandom extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<RecyclerGetterNSetter> dataHolder;
    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_random);

        dataHolder = new ArrayList<>();
//        dataHolder.add(new RecyclerGetterNSetter(R.drawable.mrplumber, 4.4, "Akash", "Plumber"));
//        dataHolder.add(new RecyclerGetterNSetter(R.drawable.mrplumber, 1.4, "Nitin", "Cleaner"));
//        dataHolder.add(new RecyclerGetterNSetter(R.drawable.mrplumber, 4.9, "XYZ", "Developer"));
//        dataHolder.add(new RecyclerGetterNSetter(R.drawable.mrplumber, 5.0, "Aadersh", "Nalla"));

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerAdapter(this, dataHolder));
        adapter = new RecyclerAdapter(this, dataHolder);


        recyclerView.setHasFixedSize(true);
        dataHolder = new ArrayList<>();
        adapter = new RecyclerAdapter(this,dataHolder);
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users/profession");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    RecyclerGetterNSetter userDetails = dataSnapshot.getValue(RecyclerGetterNSetter.class);
//                    dataHolder.add(userDetails);
                    Toast.makeText(getApplicationContext(), "Hello world", Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent home = new Intent(this,HomeActivity.class);
        startActivity(home);
        super.onBackPressed();
    }
}