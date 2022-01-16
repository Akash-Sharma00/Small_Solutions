package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class profileActivity extends AppCompatActivity {


    TextView name, age, profession, exp, contact, mail, description;
    FloatingActionButton call_Button;



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
            }
        });
    }
}