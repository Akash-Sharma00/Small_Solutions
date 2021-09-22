package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //Skip button
    public void skip(View view){
//        Toast.makeText(getBaseContext(), "Under construction", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    //Forget password button
    public void forgetPassword(View view){
        Toast.makeText(getBaseContext(), "Under construction", Toast.LENGTH_SHORT).show();
    }

    //SignIn button
    public void SignIn(View view){
        Toast.makeText(getBaseContext(), "Under construction", Toast.LENGTH_SHORT).show();
    }

    //new user button
    public void newUser(View view){
        Toast.makeText(getBaseContext(), "Under construction", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}