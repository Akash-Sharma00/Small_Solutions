package com.example.smallsolutions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_call_history, container, false);

        recyclerView = myView.findViewById(R.id.call_recycler);
        progressBar = myView.findViewById(R.id.progressBar);

        Holder = new ArrayList<>();

//        Reversing the list so last call will at top
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

//        All reference of database
        Auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        String id = Auth.getUid();
        reference = database.getReference("users/callLog/" + id);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                    Holder.add(userDetails);
                }
                progressBar.setVisibility(View.GONE);

        recyclerView.setAdapter(new CallLogAdapter(getActivity() ,Holder));

//                if no calls were detected
                if (Holder.isEmpty()){
                    TextView Null = myView.findViewById(R.id.noCalls);
                    Null.setVisibility(View.VISIBLE);
                }
            }
            @Override
                public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return myView;
    }

}
