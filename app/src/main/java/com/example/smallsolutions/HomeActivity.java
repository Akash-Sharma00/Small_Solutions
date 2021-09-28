package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    public void profile(View view){
        Intent intent = new Intent(this,profileActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView carpenter = findViewById(R.id.carpenter);
        carpenter.setBackgroundResource(R.drawable.blue);
    }
}