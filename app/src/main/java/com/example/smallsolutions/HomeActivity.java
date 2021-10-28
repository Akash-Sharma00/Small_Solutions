package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        hometxt.setTextColor(Color.rgb(143,145,146));

        hiredimg.clearColorFilter();
        hiredtxt.setTextColor(Color.rgb(143,145,146));

        profileimg.clearColorFilter();
        profiletxt.setTextColor(Color.rgb(143,145,146));

        searchimg.clearColorFilter();
        searchtxt.setTextColor(Color.rgb(143,145,146));
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

        replaceFragment(new categoryfrag());
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


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFrame, fragment);
        fragmentTransaction.commit();
    }

}