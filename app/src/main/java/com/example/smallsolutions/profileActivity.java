package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class profileActivity extends AppCompatActivity {


    TextView name, age, profession, exp, contact, mail, description;
    FloatingActionButton call_Button;
    private final int REQUEST_CODE = 1;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent(); // getting passed data

//        Creating Hooks
        name = findViewById(R.id.profile_name);
        age = findViewById(R.id.profile_age);
        profession = findViewById(R.id.profile_profession);
        exp = findViewById(R.id.profile_exp);
        contact = findViewById(R.id.profile_contact);
        mail = findViewById(R.id.profile_mail);
        call_Button = findViewById(R.id.profile_call);
        description = findViewById(R.id.profile_description);

//        Setting all data
        name.setText(intent.getStringExtra("Name"));
        age.setText(intent.getStringExtra("Age"));
        profession.setText(intent.getStringExtra("Profession"));
        exp.setText(intent.getStringExtra("Exp"));
        contact.setText(intent.getStringExtra("Contact"));
        mail.setText(intent.getStringExtra("Mail"));
        description.setText("To be given");

        call_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Tracking call logs
                uploadCalls();

//                Handle call
                makePhoneCall();
            }
        });
    }

    private void uploadCalls() {
        String receiverName = name.getText().toString();
        String receiverContact = contact.getText().toString();
        String receiverPro = profession.getText().toString();
        String callTime = new SimpleDateFormat("hh:mm  dd-MM-yyyy").format(new Date());

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users").child("callLog");
        auth = FirebaseAuth.getInstance();

        UserDetails userDetails = new UserDetails(receiverName, receiverContact, receiverPro, callTime, "Hello");
        reference.child(auth.getUid()).push().setValue(userDetails);
    }


    private void makePhoneCall() {
        String Number = contact.getText().toString();
        if (ContextCompat.checkSelfPermission(profileActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(profileActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);
        }else {
            String dial = "tel:" + Number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                makePhoneCall();
        }else {
            Toast.makeText(profileActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }

    }
}