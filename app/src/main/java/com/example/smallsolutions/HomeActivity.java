package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar,menu);
        return  true;
    }
}