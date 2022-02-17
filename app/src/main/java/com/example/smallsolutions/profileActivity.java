package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class profileActivity extends AppCompatActivity {

    TextView name, age, profession, exp, contact, mail, description;
    CircleImageView profilePhoto;
    ProgressBar progressBar;
    FloatingActionButton call_Button;
    Button connectChat;
    private final int REQUEST_CODE = 1;
    FirebaseDatabase database;
    DatabaseReference reference,ref;
    FirebaseAuth auth;

    String seeker_id;

    String imageURL;

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
        progressBar = findViewById(R.id.profile_progress);
        profilePhoto = findViewById(R.id.userProfilePhoto);
        connectChat = findViewById(R.id.chat_button);

//        Setting all data

        seeker_id = intent.getStringExtra("uid");

        imageURL = intent.getStringExtra("ProfilePhoto");

        progressBar.setVisibility(View.GONE);
        name.setText(intent.getStringExtra("Name"));
        age.setText(intent.getStringExtra("Age"));
        profession.setText(intent.getStringExtra("Profession"));
        exp.setText(intent.getStringExtra("Exp"));
        contact.setText(intent.getStringExtra("Contact"));
        mail.setText(intent.getStringExtra("Mail"));
        description.setText("To be given");
        Picasso.get().load(imageURL).into(profilePhoto);

        call_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profileActivity.this, "Connecting Call!", Toast.LENGTH_SHORT).show();

//                Tracking call logs
                uploadCalls();

//                Handle call
                makePhoneCall();
            }
        });

//        Listing all users in chat recycler

        connectChat.setOnClickListener(view -> {
            auth = FirebaseAuth.getInstance();
            if(seeker_id.equals(auth.getUid())){
                Toast.makeText(this, "You Can't Connect With Same Account", Toast.LENGTH_SHORT).show();
                return;
            }
            String pro = intent.getStringExtra("Profession");
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("users/chats/");


                ChatMessageLoader user = new ChatMessageLoader( imageURL,pro, intent.getStringExtra("Name"),seeker_id);
            reference.child(auth.getUid()).child(seeker_id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    FirebaseAuth Auth = FirebaseAuth.getInstance();

                    ref = database.getReference("users/allUsers/" + Auth.getUid());
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

//                Getting path
                            String PATH = snapshot.getValue(String.class);

                            ref = database.getReference(PATH);


                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    UserDetails userDetails = snapshot.getValue(UserDetails.class);
                                    ChatMessageLoader user2;
                                    if (PATH.contains("Recruiter")){
                                        user2 = new ChatMessageLoader( userDetails.getImageURL(),"Recruiter", userDetails.getUserName(), auth.getUid());
                                    }
                                    else {
                                        user2 = new ChatMessageLoader( userDetails.getImageURL(),pro, userDetails.getUserName(), auth.getUid());
                                    }

                                    reference.child(seeker_id).child(auth.getUid()).setValue(user2);

                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {}
                            });
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                }
            });



            startActivity(new Intent(profileActivity.this, ChatActivity.class));

        });
    }

    private void uploadCalls() {
        String receiverName = name.getText().toString().trim();
        String receiverContact = contact.getText().toString().trim();
        String receiverPro = profession.getText().toString().trim();
        String callTime = new SimpleDateFormat("hh:mm  dd-MM-yyyy").format(new Date());

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users").child("callLog");
        auth = FirebaseAuth.getInstance();

        UserDetails userDetails = new UserDetails(receiverName, receiverContact, receiverPro, callTime, imageURL);
        reference.child(auth.getUid()).push().setValue(userDetails);

    }

    private void makePhoneCall() {
        String Number = contact.getText().toString().trim();
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