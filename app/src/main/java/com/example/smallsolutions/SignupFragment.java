package com.example.smallsolutions;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignupFragment extends Fragment {

//    String arrays for drop down box
    String[] categoryArray = {"Job Seeker", "Recruiter"};
    String[] professionsArray = {"Carpenter", "Electrician", "Mechanic", "Plumber", "Web Developer", "App Developer", "Photo Editor", "Video Editor", "Digital Marketer", "Cook"};
    String[] experienceArray = {"yrs", "months"};

//    Variables to take user data from edittext
    String userNameString, userEmailString, userPasswordString, userPhoneNoString, catagoryString = "", ageString, experienceString, experienceTimeString = "", professionString ="";

//    Text Input Layout of profession autocomplete text view
    TextInputLayout textInputLayout_profession;

    //   Autocomplete TextViews for Drop Down
    AutoCompleteTextView autoCompleteTextView_category, autoCompleteTextView_profession, autoCompleteTextView_experience;

//    Sign up button
    Button next;

//  All data in edittext
    EditText nameEditText, emailEditText, passwordEditText, contactEditText, ageEditText, expEditText;

    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_signup, container, false);

        categoryAdapter(root);
        professionAdapter(root);

//        getting all edit text

        nameEditText = root.findViewById(R.id.username_signup);
        emailEditText = root.findViewById(R.id.emailid_signup);
        passwordEditText = root.findViewById(R.id.password_signup);
        contactEditText = root.findViewById(R.id.contact_edittext);
        ageEditText = root.findViewById(R.id.age);
        expEditText = root.findViewById(R.id.experience_edittext);

        next = root.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });

        experienceAdapter(root);

        textInputLayout_profession = root.findViewById(R.id.professions_drop_down);

        autoCompleteTextView_category = root.findViewById(R.id.autoComplete_catagory);

        gone(root);

        return root;
    }

    private void authenticateUser(){
//        Function to signup user
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();

        getEditTextData();
        auth.createUserWithEmailAndPassword(userEmailString, userPasswordString)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            sendDataToSignupActivity();
                        }
                        else{
                            emailEditText.setError("Email already exists");
                            emailEditText.requestFocus();
                        }
                    }
                });
    }

    private void sendDataToSignupActivity() {
        getEditTextData();
        if (validateInputs()){

            intent = new Intent(getContext(), SignupActivity.class);
            if (catagoryString.equals("Recruiter")) {
                UserDetails recruiterDetails = new UserDetails(userNameString, userEmailString, userPhoneNoString);
                intent.putExtra("userDetails", recruiterDetails);
                intent.putExtra("userPassword", userPasswordString);
                intent.putExtra("recruiter", "true");
            }
            else {
                UserDetails seekerDetails = new UserDetails(userNameString,
                        userEmailString, userPhoneNoString, ageString,experienceString + " " + experienceTimeString,
                        professionString);
                intent.putExtra("userDetails", seekerDetails);
                intent.putExtra("userPassword", userPasswordString);
                intent.putExtra("recruiter", "false");
            }
            startActivity(intent);
        }
    }

    private void getEditTextData() {
        userNameString = nameEditText.getText().toString().trim();
        userEmailString = emailEditText.getText().toString().trim();
        userPasswordString = passwordEditText.getText().toString().trim();
        userPhoneNoString = contactEditText.getText().toString().trim();

        if (catagoryString == "Job Seeker") {
            ageString = ageEditText.getText().toString().trim();
            experienceString = expEditText.getText().toString().trim();
        }
    }

    public boolean validateInputs(){
//        Checking if inputs are empty
//         1. Checking if textBoxes are empty
        if(userNameString.isEmpty()){
            nameEditText.setError("Username Required");
            nameEditText.requestFocus();
            return false;
        }
        if (userEmailString.isEmpty()){
            emailEditText.setError("Email Required");
            emailEditText.requestFocus();
            return false;
        }
        if (userPasswordString.isEmpty()){
            passwordEditText.setError("Password Required");
            passwordEditText.requestFocus();
            return false;
        }
        if (userPhoneNoString.isEmpty()){
            contactEditText.setError("Contact Required");
            contactEditText.requestFocus();
            return false;
        }
        if (userPhoneNoString.length() != 10){
            contactEditText.setError("Enter valid number");
            contactEditText.requestFocus();
            return false;
        }
        if (catagoryString.equals("Job Seeker")){
            if (ageString.isEmpty()){
                ageEditText.setError("Age Required");
                ageEditText.requestFocus();
                return false;
            }
            if (experienceString.isEmpty()){
                expEditText.setError("Experience Required");
                expEditText.requestFocus();
                return false;
            }
            if (Integer.parseInt(ageString) < 18){
                ageEditText.setError("Age should be atleast 18");
                ageEditText.requestFocus();
                return false;
            }
//            2. Checking if inner drop down is selected
            if (professionString.equals("")){
                autoCompleteTextView_profession.setError("Profession needed");
                autoCompleteTextView_profession.requestFocus();
                return false;
            }
            if (experienceTimeString.equals("")){
                autoCompleteTextView_experience.setError("Experience Required");
                autoCompleteTextView_experience.requestFocus();
                return false;
            }
        }
//        3. Checking if outer dropdown list is selected
        if (catagoryString.equals("")){
            autoCompleteTextView_category.setError("Category Required");
            autoCompleteTextView_category.requestFocus();
            return false;
        }

//        Checking general things
        if (userNameString.length() < 3){
            nameEditText.setError("Username too small");
            nameEditText.requestFocus();
            return false;
        }
        if (userNameString.length() > 20){
            nameEditText.setError("Username too large");
            nameEditText.requestFocus();
            return false;
        }
        if (userPasswordString.length() < 6){
            passwordEditText.setError("Password should be atleast 6 characters");
            passwordEditText.requestFocus();
            return false;
        }
        if (userPasswordString.length() > 15){
            passwordEditText.setError("Password is too large");
            passwordEditText.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmailString).matches()){
            emailEditText.setError("Provide valid email");
            emailEditText.requestFocus();
            return false;
        }

        return true;
    }

    public void categoryAdapter(View root) {
        autoCompleteTextView_category = root.findViewById(R.id.autoComplete_catagory);
        autoCompleteTextView_category.setInputType(0);

        ArrayAdapter<String> adapter_employee;
        adapter_employee = new ArrayAdapter<>(root.getContext(), R.layout.dropdown_textview, R.id.items_design, categoryArray);
        autoCompleteTextView_category.setSelected(true);
        autoCompleteTextView_category.setAdapter(adapter_employee);

        autoCompleteTextView_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                catagoryString = adapter_employee.getItem(i);
                invisible(root);
            }
        });
    }

    public void professionAdapter(View root) {
        autoCompleteTextView_profession = root.findViewById(R.id.autoComplete_profession);

        ArrayAdapter<String> adapterList;
        adapterList = new ArrayAdapter(root.getContext(), R.layout.dropdown_textview, R.id.items_design, professionsArray);

        autoCompleteTextView_profession.setAdapter(adapterList);

        autoCompleteTextView_profession.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                professionString = adapterList.getItem(i);
            }
        });
    }

    public void experienceAdapter(View root) {
        autoCompleteTextView_experience = root.findViewById(R.id.autoComplete_experience);
        autoCompleteTextView_experience.setInputType(0);
        ArrayAdapter<String> experienceList = new ArrayAdapter(root.getContext(), R.layout.dropdown_textview, R.id.items_design, experienceArray);
        autoCompleteTextView_experience.setAdapter(experienceList);

        autoCompleteTextView_experience.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                experienceTimeString = experienceList.getItem(i);
            }
        });
    }

    public void invisible(View root) {
        EditText age = root.findViewById(R.id.age);
        EditText experience_edittext = root.findViewById(R.id.experience_edittext);
        TextInputLayout experience_textInputLayout = root.findViewById(R.id.experience_dropdown);

        if (catagoryString == "Job Seeker") {
            textInputLayout_profession.setVisibility(View.VISIBLE);
            age.setVisibility(View.VISIBLE);
            experience_edittext.setVisibility(View.VISIBLE);
            experience_textInputLayout.setVisibility(View.VISIBLE);
        } else {
            textInputLayout_profession.setVisibility(View.GONE);
            age.setVisibility(View.GONE);
            experience_edittext.setVisibility(View.GONE);
            experience_textInputLayout.setVisibility(View.GONE);
        }
    }

    public void gone(View root){
        EditText age = root.findViewById(R.id.age);
        EditText experience_edittext = root.findViewById(R.id.experience_edittext);
        TextInputLayout experience_textInputLayout = root.findViewById(R.id.experience_dropdown);

        textInputLayout_profession.setVisibility(View.GONE);
        age.setVisibility(View.GONE);
        experience_edittext.setVisibility(View.GONE);
        experience_textInputLayout.setVisibility(View.GONE);
    }
}