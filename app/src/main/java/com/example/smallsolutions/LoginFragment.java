package com.example.smallsolutions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

//        When Forget Password clicked
        forgetPassword = root.findViewById(R.id.forget_password);

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPassword.setTextColor(Color.RED);
                resetPassword(root);
                forgetPassword.setTextColor(Color.BLUE);
            }
        });

        return root;
    }

//    Function To Reset Password
    private void resetPassword(View root) {

//        Creating EditText to getting Email
        final EditText mail = new EditText(root.getContext());
        mail.setTextColor(Color.BLACK);

//        Creating Dialog Window
        final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(root.getContext());
        passwordResetDialog.setTitle("Reset Password");
        passwordResetDialog.setMessage("Enter your Email Id To Send rReset Password Link");
        passwordResetDialog.setView(mail);

        passwordResetDialog.setCancelable(false).setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Extracting Main id and sending reset link
                String id = mail.getText().toString();
                if (id.isEmpty()){
                    Toast.makeText(getContext(), "Email is required to sent reset link", Toast.LENGTH_SHORT).show();
                    return;
                }
                Auth.sendPasswordResetEmail(id).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Reset Link sent to your email id check Your Account", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Failed! to send reset link "+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                       Closing Dialog
                dialog.dismiss();
            }
        });

        AlertDialog dialogBox = passwordResetDialog.create();
        dialogBox.show();
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
                Intent intent = new Intent(getContext(), HomeActivity.class);
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