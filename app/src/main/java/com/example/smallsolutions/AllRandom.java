package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllRandom extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progress;
    ArrayList<UserDetails> dataHolder;
    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_random);

//        Getting Specific Profession
        Intent intent = getIntent();
        String profession = intent.getStringExtra("pro");

        dataHolder = new ArrayList<>();

        progress = findViewById(R.id.All_progress);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerAdapter(this, dataHolder));
        adapter = new RecyclerAdapter(this, dataHolder);


        recyclerView.setHasFixedSize(true);
        dataHolder = new ArrayList<>();
        adapter = new RecyclerAdapter(this,dataHolder);
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();


        if (profession.equals("more")){
//            Loading all professions
            reference = database.getReference("users/profession");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        for (DataSnapshot data: dataSnapshot.getChildren()){
                            UserDetails userDetails = data.getValue(UserDetails.class);
                            dataHolder.add(userDetails);
                        }
                        progress.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else {
//            Loading only professions passed by user
            reference = database.getReference("users/profession/" + profession);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                            UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                            dataHolder.add(userDetails);
                    }
                    progress.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();

                    //        Condition for no result found
                    if (dataHolder.isEmpty()){
                        progress.setVisibility(View.GONE);
                        Toast.makeText(getApplication(), "No result found", Toast.LENGTH_SHORT).show();
                        TextView Null = findViewById(R.id.noResult);
                         Null.setVisibility(View.VISIBLE);
                          Null.setText("No Result Found for "+profession);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }



    }

    @Override
    public void onBackPressed() {
        Intent home = new Intent(this,HomeActivity.class);
        startActivity(home);
        super.onBackPressed();
    }

}