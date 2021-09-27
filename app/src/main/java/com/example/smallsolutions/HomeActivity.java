package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView c = (TextView) findViewById(R.id.carpenter);
    TextView e = (TextView) findViewById(R.id.electrician);
    TextView m = (TextView) findViewById(R.id.mechanic);
    TextView p = (TextView) findViewById(R.id.plumber);
    TextView s = (TextView) findViewById(R.id.softDev);
    TextView more = (TextView) findViewById(R.id.more);

    public void carpenter(View view){
        c.setBackgroundResource(R.drawable.blue);
        e.setBackgroundResource(R.drawable.white_rounded);
        m.setBackgroundResource(R.drawable.white_rounded);
        p.setBackgroundResource(R.drawable.white_rounded);
        s.setBackgroundResource(R.drawable.white_rounded);
        more.setBackgroundResource(R.drawable.white_rounded);
    }

    public void electrician(View view){
        e.setBackgroundResource(R.drawable.blue);
        c.setBackgroundResource(R.drawable.white_rounded);
        m.setBackgroundResource(R.drawable.white_rounded);
        p.setBackgroundResource(R.drawable.white_rounded);
        s.setBackgroundResource(R.drawable.white_rounded);
        more.setBackgroundResource(R.drawable.white_rounded);
    }

    public void mechanic(View view){
        m.setBackgroundResource(R.drawable.blue);
        e.setBackgroundResource(R.drawable.white_rounded);
        c.setBackgroundResource(R.drawable.white_rounded);
        p.setBackgroundResource(R.drawable.white_rounded);
        s.setBackgroundResource(R.drawable.white_rounded);
        more.setBackgroundResource(R.drawable.white_rounded);
    }

    public void plumber(View view){
        p.setBackgroundResource(R.drawable.blue);
        e.setBackgroundResource(R.drawable.white_rounded);
        m.setBackgroundResource(R.drawable.white_rounded);
        c.setBackgroundResource(R.drawable.white_rounded);
        s.setBackgroundResource(R.drawable.white_rounded);
        more.setBackgroundResource(R.drawable.white_rounded);
    }

    public void soft(View view){
        s.setBackgroundResource(R.drawable.blue);
        e.setBackgroundResource(R.drawable.white_rounded);
        m.setBackgroundResource(R.drawable.white_rounded);
        p.setBackgroundResource(R.drawable.white_rounded);
        c.setBackgroundResource(R.drawable.white_rounded);
        more.setBackgroundResource(R.drawable.white_rounded);
    }

    public void more(View view){
        more.setBackgroundResource(R.drawable.blue);
        e.setBackgroundResource(R.drawable.white_rounded);
        m.setBackgroundResource(R.drawable.white_rounded);
        p.setBackgroundResource(R.drawable.white_rounded);
        s.setBackgroundResource(R.drawable.white_rounded);
        c.setBackgroundResource(R.drawable.white_rounded);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        c.setBackgroundResource(R.drawable.blue);
    }
}