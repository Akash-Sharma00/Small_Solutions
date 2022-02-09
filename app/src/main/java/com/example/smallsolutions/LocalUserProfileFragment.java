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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class LocalUserProfileFragment extends Fragment {

//    Database variables
    FirebaseDatabase database;
    FirebaseAuth Auth;
    DatabaseReference reference;

//    Textview variables
    TextView name, age, profession, exp, contact, mail, description, signOut;
    ProgressBar progressBar;

//    Imageview variables
    CircleImageView profilePhoto;

//    Floating Action button for editing profile
    FloatingActionButton editProfile;

//    Variable to store if user is recruiter or not
    String isRecruiter = "false";

//    Declaring userDetails object to store user details
    UserDetails userDetails;

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
        profilePhoto = myRoot.findViewById(R.id.userProfilePhoto);
        progressBar = myRoot.findViewById(R.id.profileImgProgess);
        editProfile = myRoot.findViewById(R.id.editFloatingButton);

//        description.setText(Path); // Need to remove Later

        database = FirebaseDatabase.getInstance();
        Auth = FirebaseAuth.getInstance();

        reference = database.getReference("users/allUsers/" + Auth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                Getting path
                String PATH = snapshot.getValue(String.class);

                reference = database.getReference(PATH);

//                When a recruiter logged in
                if (PATH.contains("Recruiter")){

                    myRoot.findViewById(R.id.AGE).setVisibility(View.GONE);
                    myRoot.findViewById(R.id.PROFESSION).setVisibility(View.GONE);
                    myRoot.findViewById(R.id.EXP).setVisibility(View.GONE);
                    myRoot.findViewById(R.id.ABOUT).setVisibility(View.GONE);

                    age.setVisibility(View.GONE);
                    profession.setVisibility(View.GONE);
                    exp.setVisibility(View.GONE);

//                    Setting recruiter variable to value true
                    isRecruiter = "true";
                }

                 reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        userDetails = snapshot.getValue(UserDetails.class);


//                Setting data in profile
                        name.setText(userDetails.getUserName());
                        age.setText(userDetails.getAge());
                        profession.setText(userDetails.getProfession());
                        exp.setText(userDetails.getExperience());
                        contact.setText(userDetails.getUserPhoneNo());
                        mail.setText(userDetails.getUserEmail());
                        Picasso.get().load(userDetails.getImageURL()).into(profilePhoto);
                        progressBar.setVisibility(View.GONE);
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
                builder.setMessage("Are sure, You want to Sigh Out");
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

//        Calling Edit Profile
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                intent.putExtra("recruiter", isRecruiter);
                intent.putExtra("imageURL", userDetails.getImageURL());
                startActivity(intent);
            }
        });
        return myRoot;
    }
}