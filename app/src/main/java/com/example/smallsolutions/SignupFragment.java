package com.example.smallsolutions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class SignupFragment extends Fragment {

    AutoCompleteTextView autoCompleteTextView;    //for category

    //    String arrays for drop down box
    String[] catagory = {"Job Seeker", "Recruiter"};
    String[] professions = {"Carpenter", "Electrician", "Mechanic", "Plumber", "Web Developer", "App Developer", "Photo Editor", "Video Editor", "Digital Marketer", "Cook", "Other"};
    String[] experience = {"yrs", "months"};
    TextView addText, removeText;

    //    Array and adapter for profession drop down
    ArrayList<ProfessionRecyclerI> professionArray;
    ArrayAdapter adapterList;

    //    Recycler and recycler adapter
    RecyclerView recycler;
    ProfessionAdapter recyclerAdapter;

    //    Drop Down view for catagory
    AutoCompleteTextView autoCompleteTextView_category;

    //    Selected catagory String variable
    String catagory_string;

//    Sign up button
    Button signup;

//All data in edittext
    EditText name, mail, password, contact, age, exp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_signup, container, false);

        catatoryAdapter(root);
        professionAdapter(root);

//        getting all edit text

        name = root.findViewById(R.id.username_signup);
        mail = root.findViewById(R.id.emailid_signup);
        password = root.findViewById(R.id.password_signup);
        contact = root.findViewById(R.id.contact_edittext);
        age = root.findViewById(R.id.age);
        autoCompleteTextView_category = root.findViewById(R.id.autoComplete_catagory);

        addText = root.findViewById(R.id.add_profession);
        removeText = root.findViewById(R.id.remove_profession);

        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProfession(root);
            }
        });

        removeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeProfession();
            }
        });

        signup = root.findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataInFirebase();
            }
        });

        experienceAdapter(root);

        autoCompleteTextView_category = root.findViewById(R.id.autoComplete_catagory);
        gone(root);

        return root;
    }

    private void addDataInFirebase() {
    }

    public void catatoryAdapter(View root) {
        autoCompleteTextView = root.findViewById(R.id.autoComplete_catagory);
        autoCompleteTextView.setInputType(0);

        ArrayAdapter<String> adapter_employee;
        adapter_employee = new ArrayAdapter<>(root.getContext(), R.layout.dropdown_textview, R.id.items_design, catagory);
        autoCompleteTextView.setSelected(true);
        autoCompleteTextView.setAdapter(adapter_employee);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                catagory_string = adapter_employee.getItem(i);
                invisible(root);
            }
        });
    }

    public void professionAdapter(View root) {
        recycler = root.findViewById(R.id.recycler);
        adapterList = new ArrayAdapter(root.getContext(), R.layout.dropdown_textview, R.id.items_design, professions);
        professionArray = new ArrayList<>();
        professionArray.add(new ProfessionRecyclerI(adapterList));
        recyclerAdapter = new ProfessionAdapter(professionArray);
        recycler.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recycler.setAdapter(recyclerAdapter);
    }

    public void addProfession(View root) {
        professionArray.add(0, new ProfessionRecyclerI(adapterList));
        recyclerAdapter.notifyItemInserted(0);
    }

    public void removeProfession() {
        if (professionArray.size() > 1) {
            professionArray.remove(0);
            recyclerAdapter.notifyItemRemoved(0);
        }
    }

    public void experienceAdapter(View root) {
        AutoCompleteTextView autoCompleteTextView_experience;
        autoCompleteTextView_experience = root.findViewById(R.id.autoComplete_experience);
        autoCompleteTextView_experience.setInputType(0);
        ArrayAdapter exprienceList = new ArrayAdapter(root.getContext(), R.layout.dropdown_textview, R.id.items_design, experience);
        autoCompleteTextView_experience.setAdapter(exprienceList);
    }

    public void invisible(View root) {
        EditText age = root.findViewById(R.id.age);
        EditText experience_edittext = root.findViewById(R.id.experience_edittext);
        TextInputLayout experience_textInputLayout = root.findViewById(R.id.experience_dropdown);

        if (catagory_string == "Job Seeker") {
            recycler.setVisibility(View.VISIBLE);
            age.setVisibility(View.VISIBLE);
            experience_edittext.setVisibility(View.VISIBLE);
            experience_textInputLayout.setVisibility(View.VISIBLE);
            addText.setVisibility(View.VISIBLE);
            removeText.setVisibility(View.VISIBLE);
        } else {
            recycler.setVisibility(View.GONE);
            age.setVisibility(View.GONE);
            experience_edittext.setVisibility(View.GONE);
            experience_textInputLayout.setVisibility(View.GONE);
            addText.setVisibility(View.GONE);
            removeText.setVisibility(View.GONE);
        }
    }

    public void gone(View root){
        EditText age = root.findViewById(R.id.age);
        EditText experience_edittext = root.findViewById(R.id.experience_edittext);
        TextInputLayout experience_textInputLayout = root.findViewById(R.id.experience_dropdown);

        recycler.setVisibility(View.GONE);
        age.setVisibility(View.GONE);
        experience_edittext.setVisibility(View.GONE);
        experience_textInputLayout.setVisibility(View.GONE);
        addText.setVisibility(View.GONE);
        removeText.setVisibility(View.GONE);
    }
}