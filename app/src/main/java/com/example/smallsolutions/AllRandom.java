package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AllRandom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_random);

    }

    @Override
    public void onBackPressed() {
        Intent home = new Intent(this,HomeActivity.class);
        startActivity(home);
        super.onBackPressed();
    }
}