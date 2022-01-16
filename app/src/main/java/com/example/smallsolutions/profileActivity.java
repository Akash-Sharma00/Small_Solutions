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

public class profileActivity extends AppCompatActivity {


    TextView name, age, profession, exp, contact, mail, description;
    FloatingActionButton call_Button;
    private int REQUEST_CODE = 1;



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
//                Handle call
                makePhoneCall();

//                Tracking call logs

            }
        });
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