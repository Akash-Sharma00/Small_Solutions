package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class More extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        getActionBar().setTitle("Categories");
    }
}