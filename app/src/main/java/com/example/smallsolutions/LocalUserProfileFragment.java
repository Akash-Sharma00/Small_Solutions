package com.example.smallsolutions;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LocalUserProfileFragment extends Fragment {

    FirebaseDatabase database;
    FirebaseAuth Auth;
    DatabaseReference reference;

    TextView name, age, profession, exp, contact, mail, description, signOut;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myRoot = inflater.inflate(R.layout.fragment_local_user_profile, container, false);

//      Creating Hooks
        name = myRoot.findViewById(R.id.local_name);
        age = myRoot.findViewById(R.id.local_age);
        profession = myRoot.findViewById(R.id.local_profession);
        exp = myRoot.findViewById(R.id.local_exp);
        contact = myRoot.findViewById(R.id.local_contact);
        mail = myRoot.findViewById(R.id.local_mail);
        description = myRoot.findViewById(R.id.local_description);


//        description.setText(Path); // Need to remove Later

        database = FirebaseDatabase.getInstance();
        Auth = FirebaseAuth.getInstance();

        reference = database.getReference("users/allUsers/" + Auth.getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                Getting path
                String PATH = snapshot.getValue(String.class);

                reference = database.getReference(PATH);

//                When a recruiter logged in
                if (PATH.contains("Recruiter")){
                    Toast.makeText(getActivity(), "Found", Toast.LENGTH_SHORT).show();

                    myRoot.findViewById(R.id.AGE).setVisibility(View.GONE);
                    myRoot.findViewById(R.id.PROFESSION).setVisibility(View.GONE);
                    myRoot.findViewById(R.id.EXP).setVisibility(View.GONE);
                    myRoot.findViewById(R.id.ABOUT).setVisibility(View.GONE);

                    age.setVisibility(View.GONE);
                    profession.setVisibility(View.GONE);
                    exp.setVisibility(View.GONE);
                }

                 reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        UserDetails userDetails = snapshot.getValue(UserDetails.class);

//                Setting data in profile
                        name.setText(userDetails.getUserName());
                        age.setText(userDetails.getAge());
                        profession.setText(userDetails.getProfession());
                        exp.setText(userDetails.getExperience());
                        contact.setText(userDetails.getUserPhoneNo());
                        mail.setText(userDetails.getUserEmail());

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

//        Signing Out the user
        signOut = myRoot.findViewById(R.id.signOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Alert Box to confirmation
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder.setMessage("Are sure, You want to Exit");
                builder.create();
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Auth.signOut();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                             Close
                    }
                });
                builder.create().show();
            }
        });
        return myRoot;
    }
}