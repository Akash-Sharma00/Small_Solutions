package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private final int PICK_IMAGE = 25;

//    Constant String value to store default image location
    String defaultImageURL;

//    Variable to store user id and path to userdetails in database
    String userID, path;

//    UserDetails class instance
    UserDetails userDetails;

//    Declaring all view holders
    ImageView profileImage;
    Button signupButton, editButton;
    ProgressBar progressBar;

//    Image URI
    Uri imageUri;

//    Intent object to get userDetails from signupFragment
    Intent userDetailsIntent;

//    String to get data from intent
    String recruiter, userPassword;

//    Bitmap
    Bitmap bitmap;

//    Firebase Variables
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        Assigning id's to view holders
        profileImage = findViewById(R.id.profilePhoto);
        editButton = findViewById(R.id.edit_button);
        signupButton = findViewById(R.id.signUp_button);
        progressBar = findViewById(R.id.signUpProgress);

//        Setting a default image to profile photo
        defaultImageURL = "https://firebasestorage.googleapis.com/v0/b/small-solutions-8d943.appspot.com/o/default%2Fdefault.png?alt=media&token=c334a4e3-2d71-4795-a5e4-a27e54862520";
        Picasso.get().load(defaultImageURL)
                .into(profileImage);

//        Firebase variables
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");
        storageReference = FirebaseStorage.getInstance().getReference();

//        Getting intent
        userDetailsIntent = getIntent();
        userDetails = (UserDetails) userDetailsIntent.getSerializableExtra("userDetails");
        userPassword = userDetailsIntent.getStringExtra("userPassword");
        recruiter = userDetailsIntent.getStringExtra("recruiter");

//        Adding click listeners to all view holders
        profileImage.setOnClickListener(this);
        editButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);
    }

//    Onclick function to handle clicks of view holders
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profilePhoto:
//                Handle profile photo click listener here
                break;
            case R.id.edit_button:
                selectImage();
                break;
            case R.id.signUp_button:
                signupButton.setClickable(false);
                signupButton.setText("Signing up ...");
                setImageURL();
                break;
        }
    }

//    Function to select image from local files
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile image"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImage.setImageBitmap(bitmap);
                editButton.setText("Change Image");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    Setting imageURL to userObject to database
    private void setImageURL() {

        if (imageUri == null){
            userDetails.setImageURL(defaultImageURL);
            uploadUserObject();
        }
        else{
            StorageReference ref = storageReference.child("images/" + auth.getUid());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 5, baos);
            byte [] data = baos.toByteArray();

            UploadTask uploadTask = ref.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignupActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            userDetails.setImageURL(uri.toString());
                            uploadUserObject();
                        }
                    });
                }
            });
        }
    }

//    Uploading user data in data
    private void uploadUserObject(){
//        Setting userID
        userID = auth.getUid();
        userDetails.setUid(userID);

//        Uploading data to database
        if (recruiter.equals("true")){
            databaseReference.child("Recruiter").child(userID).setValue(userDetails);
            databaseReference.child("Recruiter").child(userID).child("imageURL").setValue(userDetails.getImageURL());
            path = "users/Recruiter/" + userID;
        }else {
            databaseReference.child("profession").child(userDetails.getProfession()).child(userID).setValue(userDetails);
            databaseReference.child("profession").child(userDetails.getProfession()).child(userID).child("imageURL").setValue(userDetails.getImageURL());
            path = "users/profession/" + userDetails.getProfession() + "/" + userID;
        }
        databaseReference.child("allUsers").child(userID).setValue(path);

        progressBar.setVisibility(View.GONE);

        Toast.makeText(SignupActivity.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignupActivity.this, HomeActivity.class));
    }

    @Override
    protected void onStop() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseAuth.getInstance().signOut();
        user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                finish();
            }
        });
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseAuth.getInstance().signOut();
        if (user != null){
            user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                }
            });
        }
        super.onDestroy();
    }
}