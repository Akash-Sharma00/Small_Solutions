package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
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
        TextView electrician = findViewById(R.id.electrician);
        TextView mechanic = findViewById(R.id.mechanic);
        TextView softDev = findViewById(R.id.softDev);
        TextView plumber = findViewById(R.id.plumber);
        TextView more = findViewById(R.id.more);
        carpenter.setBackgroundResource(R.drawable.blue);

        electrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                electrician.setBackgroundResource(R.drawable.blue);
                mechanic.setBackgroundResource(R.drawable.white);
                softDev.setBackgroundResource(R.drawable.white);
                carpenter.setBackgroundResource(R.drawable.white);
                plumber.setBackgroundResource(R.drawable.white);
                more.setBackgroundResource(R.drawable.white);
            }
        });

        mechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mechanic.setBackgroundResource(R.drawable.blue);
                electrician.setBackgroundResource(R.drawable.white);
                softDev.setBackgroundResource(R.drawable.white);
                carpenter.setBackgroundResource(R.drawable.white);
                plumber.setBackgroundResource(R.drawable.white);
                more.setBackgroundResource(R.drawable.white);
            }
        });

        softDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                softDev.setBackgroundResource(R.drawable.blue);
                mechanic.setBackgroundResource(R.drawable.white);
                electrician.setBackgroundResource(R.drawable.white);
                carpenter.setBackgroundResource(R.drawable.white);
                plumber.setBackgroundResource(R.drawable.white);
                more.setBackgroundResource(R.drawable.white);
            }
        });

        carpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carpenter.setBackgroundResource(R.drawable.blue);
                mechanic.setBackgroundResource(R.drawable.white);
                softDev.setBackgroundResource(R.drawable.white);
                electrician.setBackgroundResource(R.drawable.white);
                plumber.setBackgroundResource(R.drawable.white);
                more.setBackgroundResource(R.drawable.white);
            }
        });

        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plumber.setBackgroundResource(R.drawable.blue);
                mechanic.setBackgroundResource(R.drawable.white);
                softDev.setBackgroundResource(R.drawable.white);
                carpenter.setBackgroundResource(R.drawable.white);
                electrician.setBackgroundResource(R.drawable.white);
                more.setBackgroundResource(R.drawable.white);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, More.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int choice = item.getItemId();
        if (choice == R.id.search){
            Intent intent = new Intent(HomeActivity.this, More.class);
            startActivity(intent);        }
        if (choice == R.id.category){
            Intent intent = new Intent(HomeActivity.this, More.class);
            startActivity(intent);
        }
        if (choice == R.id.settings){
            Toast.makeText(this, "Under Construction", Toast.LENGTH_SHORT).show();
        }
        if (choice == R.id.login){
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}