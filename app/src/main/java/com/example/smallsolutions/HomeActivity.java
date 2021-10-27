package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    public void catSearch(View view){
        Intent intent = new Intent(this,Category.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView img = findViewById(R.id.imghome);
        img.setColorFilter(R.drawable.blue);
    }
}