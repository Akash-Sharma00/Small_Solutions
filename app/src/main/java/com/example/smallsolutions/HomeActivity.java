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
}