package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        carpenter.setBackgroundResource(R.drawable.blue);
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
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
        }
        if (choice == R.id.category){
            Toast.makeText(this, "Under Construction", Toast.LENGTH_SHORT).show();
        }
        if (choice == R.id.settings){
            Toast.makeText(this, "Under Construction", Toast.LENGTH_SHORT).show();
        }
        if (choice == R.id.login){
            Toast.makeText(this, "Under Construction", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}