package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    TextView name, age, profession, exp, contact, mail, description;
    Button call_Button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String Path = intent.getStringExtra("PATH");  //PAth variable

//      Creating Hooks
        name = findViewById(R.id.profile_name);
        age = findViewById(R.id.profile_age);
        profession = findViewById(R.id.profile_profession);
        exp = findViewById(R.id.profile_exp);
        contact = findViewById(R.id.profile_contact);
        mail = findViewById(R.id.profile_mail);
        description = findViewById(R.id.profile_description);
        call_Button = findViewById(R.id.call_button);

        description.setText(Path); // Need to remove Later

        database = FirebaseDatabase.getInstance();
        reference = database.getReference(Path);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UserDetails userDetails = snapshot.getValue(UserDetails.class);

//                Setting data in profile
                name.setText(userDetails.getUserName());
                age.setText(userDetails.getAge());
                profession.setText(userDetails.getProfession());
                exp.setText(userDetails.getExperience());
                contact.setText(userDetails.getUserPhoneNo());
                mail.setText(userDetails.getUserEmail());
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}