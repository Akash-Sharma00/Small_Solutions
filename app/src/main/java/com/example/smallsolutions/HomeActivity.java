package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    TextView c = (TextView) findViewById(R.id.carpenter);
    TextView e = (TextView) findViewById(R.id.electrician);
    TextView m = (TextView) findViewById(R.id.mechanic);
    TextView p = (TextView) findViewById(R.id.plumber);
    TextView s = (TextView) findViewById(R.id.softDev);
    TextView more = (TextView) findViewById(R.id.more);

    public void carpenter(View view){
        c.setBackgroundResource(R.drawable.blue);
        Toast.makeText(this, "Rest all button are under developing stage", Toast.LENGTH_SHORT).show();
    }

    public void electrician(View view){
        c.setBackgroundResource(R.drawable.white_rounded);
    }

    public void mechanic(View view){

    }

    public void plumber(View view){

    }

    public void soft(View view){

    }

    public void more(View view){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        c.setBackgroundResource(R.drawable.blue);
    }
}