package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    ImageView homeimg, searchimg, hiredimg, profileimg;
    TextView hometxt, searchtxt, hiredtxt, profiletxt;

    public void getElement(){
        homeimg = findViewById(R.id.imghome);
        searchimg = findViewById(R.id.imgsearch);
        hiredimg = findViewById(R.id.imghired);
        profileimg = findViewById(R.id.imgprofile);

        hometxt = findViewById(R.id.hometxt);
        searchtxt = findViewById(R.id.cattxt);
        hiredtxt = findViewById(R.id.hiredtxt);
        profiletxt = findViewById(R.id.profiletxt);
    }

    public  void clearColor(){
        getElement();
        homeimg.clearColorFilter();
        hometxt.setTextColor(R.drawable.search_bar_bg);

        hiredimg.clearColorFilter();
        hiredtxt.setTextColor(R.drawable.search_bar_bg);

        profileimg.clearColorFilter();
        profiletxt.setTextColor(R.drawable.search_bar_bg);

        searchimg.clearColorFilter();
        searchtxt.setTextColor(R.drawable.search_bar_bg);
    }

    public void homepage(View view){
        getElement();
        clearColor();
        homeimg.setColorFilter(Color.BLUE);
        hometxt.setTextColor(Color.BLUE);
    }

    public void catepage(View view){
        getElement();
        clearColor();
        searchimg.setColorFilter(Color.BLUE);
        searchtxt.setTextColor(Color.BLUE);
    }

    public void hiredpage(View view){
        getElement();
        clearColor();
        hiredtxt.setTextColor(Color.BLUE);
        hiredimg.setColorFilter(Color.BLUE);
    }

    public void profilepage(View view){
        getElement();
        clearColor();
        profileimg.setColorFilter(Color.BLUE);
        profiletxt.setTextColor(Color.BLUE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
}