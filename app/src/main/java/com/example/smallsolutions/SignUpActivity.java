package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private RadioGroup radioGroup;
    Button cancel, done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //getting buttons and views
        cancel = (Button) findViewById(R.id.cancel);
        done = (Button) findViewById(R.id.done);
        radioGroup = (RadioGroup) findViewById(R.id.selections);
        radioGroup.clearCheck();
        ScrollView scrollView = (ScrollView) findViewById(R.id.form);
        scrollView.setVisibility(View.INVISIBLE);
        TextView t2 = (TextView) findViewById(R.id.exp);
        TextView t3 = (TextView) findViewById(R.id.age);

        //Set up the drop down list
        Spinner spinner = (Spinner) findViewById(R.id.professions);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        //Creating List
        List<String> categories = new ArrayList<String>();
        categories.add("Choose  Your Profession");
        categories.add("Carpenter");
        categories.add("Plumber");
        categories.add("Mechanic");
        categories.add("Electrician");
        categories.add("Software Developer");
        categories.add("Teacher");
        categories.add("Web Developer");
        categories.add("Therapist");
        categories.add("Marketing Analyst");
        categories.add("Computer System Analyst");
        categories.add("OTHERS");

        //Setting Adapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Adding List in Drop
        spinner.setAdapter(dataAdapter);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                RadioButton employee = (RadioButton) findViewById(R.id.employee);
                RadioButton employer = (RadioButton) findViewById(R.id.employer);
                if (radioButton == employee || radioButton == employer) {
                    scrollView.setVisibility(View.VISIBLE);
                    if (radioButton == employer) {
                        spinner.setVisibility(View.INVISIBLE);
                        t2.setVisibility(View.INVISIBLE);
                        t3.setVisibility(View.INVISIBLE);
                    } else {
                        spinner.setVisibility(View.VISIBLE);
                        t2.setVisibility(View.VISIBLE);
                        t3.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    //Function run when the drop down list clicked
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "You selected "+item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Default function
    }

    //When cancel clicked
    public void CANCEL (View view)
    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}