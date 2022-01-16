package com.example.smallsolutions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LocalUserProfileFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference reference;

    TextView name, age, profession, exp, contact, mail, description;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myRoot = inflater.inflate(R.layout.fragment_local_user_profile, container, false);

        HomeActivity homeActivity = (HomeActivity) getActivity();
        String PATH = homeActivity.getPath();
        Toast.makeText(getContext(), PATH, Toast.LENGTH_SHORT).show();


        Toast.makeText(getContext(), PATH, Toast.LENGTH_SHORT).show();

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
        reference = database.getReference(PATH);

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
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return myRoot;
    }
}