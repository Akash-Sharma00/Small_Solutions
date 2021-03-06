package com.example.smallsolutions;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    EditText username, password;
    TextView forgetPassword;
    Button login;
    ProgressBar progressBar;
    FirebaseAuth Auth = FirebaseAuth.getInstance(); //getting Database reference

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        checkIfUserIsLoggedIn();

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        animation(root);

        progressBar = root.findViewById(R.id.login_progress);

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
                resetPassword(root);
            }
        });

        return root;
    }

//    Function To Reset Password
    private void resetPassword(View root) {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.forgetpasswordbox);

//        Creating Hooks
        EditText mail = dialog.findViewById(R.id.getMail);
        Button cancel = dialog.findViewById(R.id.cancel);
        Button confirm = dialog.findViewById(R.id.confirm);
        
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Extracting email id
                String id = mail.getText().toString();

//                Condition For empty string
                if (id.isEmpty()){
                    Toast.makeText(getContext(), "Email is required to send reset link", Toast.LENGTH_SHORT).show();
                    return;
                }
//                Sending reset link
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

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //    Function to Authenticate user's id and password.
    private void authenticateUser(View root) {

        progressBar.setVisibility(View.VISIBLE);

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
            progressBar.setVisibility(View.GONE);
            return;
        }else if(code.isEmpty()){
            password.setError("Password is Required");
            password.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        Auth.signInWithEmailAndPassword(mail, code).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), HomeActivity.class));
                    getActivity().finish();
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Error: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
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

    public void checkIfUserIsLoggedIn(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null){
            getActivity().finish();
        }
    }
}