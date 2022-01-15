package com.example.smallsolutions;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginFragment extends Fragment {

    EditText username, password;
    TextView forgetPassword;
    Button login;
    FirebaseAuth Auth = FirebaseAuth.getInstance(); //getting Database reference

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        animation(root);

//        When log in button clicked
        login = root.findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser(root);
            }
        });

        return root;
    }

//    Function to Authenticate user's id and password.
    private void authenticateUser(View root) {


//      Hooks for id pass word
        username = root.findViewById(R.id.UserName_login);
        password = root.findViewById(R.id.password_login);

//        Storing user's id and password
        String mail = username.getText().toString().trim();
        String code = password.getText().toString().trim();

//        Conditions for passing empty values
        if (mail.isEmpty()){
            username.setError("Email is Required");
            username.requestFocus();
            return;
        }else if(code.isEmpty()){
            password.setError("Password is Required");
            password.requestFocus();
            return;
        }

        Auth.signInWithEmailAndPassword(mail, code).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
                    gettingUserPath(Auth.getCurrentUser().getUid());
                }
                else {
                    Toast.makeText(getContext(), "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

//    Function to getting path of user's profile
    private void gettingUserPath(String UID) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users").child("allUsers").child(UID);
       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
//               Passing path to the profile activity
                String path =  snapshot.getValue(String.class);
                Intent intent = new Intent(getContext(), profileActivity.class);
                intent.putExtra("PATH",path);
                startActivity(intent);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
    }

    //    Function to create animation for login page
    public void animation(View root){

//        Creating Hooks
        username = root.findViewById(R.id.UserName_login);
        password = root.findViewById(R.id.password_login);
        forgetPassword = root.findViewById(R.id.forget_password);
        login = root.findViewById(R.id.login_button);

//        Setting initial opacity for all elements
        username.setAlpha(0);
        password.setAlpha(0);
        forgetPassword.setAlpha(0);
        login.setAlpha(0);

//        Setting initial translation
        username.setTranslationX(200);
        password.setTranslationX(200);
        forgetPassword.setTranslationX(200);
        login.setTranslationX(200);

//        Creating animation
        username.animate().translationX(0).alpha(1).setDuration(800);
        password.animate().translationX(0).alpha(1).setDuration(950);
        forgetPassword.animate().translationX(0).alpha(1).setDuration(1100);
        login.animate().translationX(0).alpha(1).setDuration(1250);
    }
}