package com.example.smallsolutions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CallHistory extends Fragment {


    RecyclerView recyclerView;
    ArrayList<UserDetails> Holder;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth Auth;
    CallLogAdapter Adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_call_history, container, false);

        recyclerView = myView.findViewById(R.id.call_recycler);
        Holder = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CallLogAdapter(Holder));
        recyclerView.setAdapter(Adapter);

//        UserDetails u = new UserDetails("Akash", "9876", "Dev", "1-24-2022  10:4", "Hello");
//        Holder.add(u);
//        Holder.add(u);
//        Holder.add(u);
//        Holder.add(u);
//        Holder.add(u);
//        Holder.add(u);
//        Holder.add(u);
//        Holder.add(u);
//        Holder.add(u);


        Auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        String id = Auth.getUid();
        reference = database.getReference("users/callLog/" + id);

//        Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT).show();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                    Toast.makeText(getActivity(), userDetails.getTime(), Toast.LENGTH_SHORT).show();
                    Holder.add(userDetails);
                }
                Adapter.notifyDataSetChanged();
            }
            @Override
                public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return myView;
    }
}