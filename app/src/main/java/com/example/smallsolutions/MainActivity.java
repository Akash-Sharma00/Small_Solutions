package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    //Variables
    Animation topAnim, bottomAnim, bottomImageAnim;
    ImageView image, bottomImage;
    TextView appName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        bottomImageAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_image_animation);

        //Hooks
        image = findViewById(R.id.image);
        appName = findViewById(R.id.textView);
        bottomImage = findViewById(R.id.imageView);

        image.setAnimation(topAnim);
        appName.setAnimation(bottomAnim);
        bottomImage.setAnimation(bottomImageAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

//      Condition for no internet
        if (!internetIsActive()){
            startActivity(new Intent(MainActivity.this, No_Internet_Activity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseDatabase database;
        DatabaseReference reference;
        FirebaseAuth Auth;

        Auth = FirebaseAuth.getInstance();
        FirebaseUser user = Auth.getCurrentUser();
        if (user != null){
            database = FirebaseDatabase.getInstance();
            String UID = Auth.getCurrentUser().getUid();
            reference = database.getReference("users").child("allUsers").child(UID);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String path =  snapshot.getValue(String.class);
                    Intent intent;
                    intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

//    Function to check device has internet or not
    private boolean internetIsActive() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
             connected = true;
        }
        else
            connected = false;
        return connected;
    }

}