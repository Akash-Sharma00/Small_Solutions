package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

//    Progress bar
    ProgressBar progressBar, uploadProgressBar;

//    Variables to creates hooks for ui elements
    CircleImageView profileEdit;
    ImageView imageEditPencil;
    TextView removeProfilePicture;
    TextInputEditText userNameUpdate, userContactUpdate, userAgeUpdate, userExperienceUpdate;
    TextInputLayout professionTIL, experienceTIL;
    AutoCompleteTextView experienceDropDown, userProfessionUpdate;
    Button update;

//    Toolbar elements
    Toolbar toolbar;
    ImageView back, confirmChanges;

//    Strings needed to be updated
    String name, email, password, phoneNo, profession, age, experienceNo, experienceDropDownValue;

//    URI and bitmaps
    Uri imageURI = null;
    Bitmap bitmap;

//    String array for drop down
    String[] exp = {"yrs", "months"};
    String[] professionDropDown = {"Carpenter", "Electrician", "Mechanic", "Plumber", "Web Developer", "App Developer", "Photo Editor", "Video Editor", "Digital Marketer", "Cook"};

//    String to store image download url
    String imageURL;

//    String variable to get value of isRecruiter variable passed through intent
    String recruiter;

//    UserDetaiils object to store value of user passed through intent
    UserDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        progressBar = findViewById(R.id.progressBar);
        uploadProgressBar = findViewById(R.id.confirmChangesProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        uploadProgressBar.setVisibility(View.GONE);

//        Hooks for toolbar elements
        toolbar = findViewById(R.id.toolbar);
        confirmChanges = findViewById(R.id.confirmChanges);
        back = findViewById(R.id.back);
        setSupportActionBar(toolbar);

//        Hooks for ui elements
        profileEdit = findViewById(R.id.profilePhotoEdit);
        imageEditPencil = findViewById(R.id.editProfilePhoto);
        removeProfilePicture = findViewById(R.id.removeProfilePicture);
        userNameUpdate = findViewById(R.id.userNameUpdate);
        userContactUpdate = findViewById(R.id.userPhoneNoUpdate);
        userProfessionUpdate = findViewById(R.id.autocomplete_user_profession);
        userAgeUpdate = findViewById(R.id.userAgeNoUpdate);
        userExperienceUpdate = findViewById(R.id.userExperienceNoUpdate);
        experienceDropDown = findViewById(R.id.autoComplete_experience_update);
        update = findViewById(R.id.updateChanges);
        professionTIL = findViewById(R.id.userProfessionNoTIL);
        experienceTIL = findViewById(R.id.userExperienceUpdateDropDown);

//        Setting adapters for  drop down
        ArrayAdapter<String> expList;
        expList = new ArrayAdapter(this, R.layout.dropdown_textview, R.id.items_design, exp);
        experienceDropDown.setAdapter(expList);

        ArrayAdapter<String> proList;
        proList = new ArrayAdapter(this, R.layout.dropdown_textview, R.id.items_design, professionDropDown);
        userProfessionUpdate.setAdapter(proList);

        Intent intent = getIntent();
        recruiter = intent.getStringExtra("recruiter");
        userDetails = (UserDetails) intent.getSerializableExtra( "userDetails");

        if (recruiter.equals("true")){
//            Function call to make unnecessary elements invisible
            visibilityGone();
        }

//        Function call to set Edit text values to user details as default
        setDefaultValues();

        back.setOnClickListener(this);
        confirmChanges.setOnClickListener(this);
        profileEdit.setOnClickListener(this);
        imageEditPencil.setOnClickListener(this);
        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                createDatabaseInstance();
                break;
            case R.id.profilePhotoEdit:
            case R.id.editProfilePhoto:
                selectImage();
                break;
            case R.id.confirmChanges:
            case R.id.updateChanges:
                uploadProgressBar.setVisibility(View.VISIBLE);
                uploadDataToDatabase();
                break;
        }
    }

    private void setDefaultValues() {
        Picasso.get().load(userDetails.getImageURL()).into(profileEdit);
        userNameUpdate.setText(userDetails.getUserName());
        userContactUpdate.setText(userDetails.getUserPhoneNo());

        if (!recruiter.equals("true")){
            userProfessionUpdate.setText(userDetails.getProfession());
            userAgeUpdate.setText(userDetails.getAge());

            String[] exp = userDetails.getExperience().split(" ",2);
            userExperienceUpdate.setText(exp[0]);
            experienceDropDown.setText(exp[1]);
        }

        progressBar.setVisibility(View.GONE);
    }

    private void visibilityGone() {
        professionTIL.setVisibility(View.GONE);
        userAgeUpdate.setVisibility(View.GONE);
        userExperienceUpdate.setVisibility(View.GONE);
        experienceTIL.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        createDatabaseInstance();
    }

    private void displayAlert(){
//        Checking if user had made any changes via function call
        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
        builder.setCancelable(false);
        builder.setMessage("Changes made will be Discarded, exit anyway?");
        builder.create();
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                             Close
            }
        });
        builder.create().show();
    }

    private boolean compareChanges(UserDetails userDetailsDatabase){
//        Getting values in edit texts
        getEditTextValue();

//        Getting image URI
        if (imageURI != null){
            return false;
        }

//        Checking if any changes were done
        if (!name.equals(userDetailsDatabase.getUserName())){
            return false;
        }
        if (!phoneNo.equals(userDetailsDatabase.getUserPhoneNo())){
            return false;
        }
        if (recruiter.equals("true")){
            if (!profession.equals(userDetailsDatabase.getProfession())){
                return false;
            }
            String experience = experienceNo + experienceDropDownValue;
            if (!experience.equals(userDetailsDatabase.getExperience())){
                return false;
            }
        }
        return true;
    }
    private void getEditTextValue(){
        name = userNameUpdate.getText().toString().trim();
        phoneNo = userContactUpdate.getText().toString().trim();

        if (!recruiter.equals("true")){
            profession = userProfessionUpdate.getText().toString().trim();
            age = userAgeUpdate.getText().toString().trim();
            experienceNo = userExperienceUpdate.getText().toString().trim();
            experienceDropDownValue = experienceDropDown.getText().toString().trim();
        }
    }

    private void createDatabaseInstance(){
        FirebaseDatabase database;
        DatabaseReference databaseReference;
        FirebaseAuth auth;

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        databaseReference = database.getReference("users/allUsers/" + auth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                To store the path of user object in database
                String path = snapshot.getValue(String.class);
                FirebaseDatabase database;
                database = FirebaseDatabase.getInstance();
                DatabaseReference userReference = database.getReference(path);
                userReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserDetails userDetailsDatabase = snapshot.getValue(UserDetails.class);
                        if (!compareChanges(userDetailsDatabase)){
                            displayAlert();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile image"), 20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageURI = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageURI);
                profileEdit.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadDataToDatabase(){
        FirebaseDatabase database;
        FirebaseAuth auth;
        DatabaseReference reference;

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        if (imageURI != null){
            StorageReference storageReference;
            storageReference = FirebaseStorage.getInstance().getReference();
            StorageReference ref = storageReference.child("images/" + auth.getUid());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 5, baos);
            byte [] data = baos.toByteArray();

            UploadTask uploadTask = ref.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imageURL = uri.toString();
                            reference.child("Recruiter").child(auth.getUid()).child("imageURL").setValue(imageURL);
                        }
                    });
                }
            });
        }

        reference.child("Recruiter").child(auth.getUid()).child("userName").setValue(name);
        reference.child("Recruiter").child(auth.getUid()).child("userPhoneNo").setValue(phoneNo);
        if (!recruiter.equals("true")){
            reference.child("profession").child(profession).child(auth.getUid()).child("userName").setValue(name);
        }
        uploadProgressBar.setVisibility(View.GONE);
    }
}