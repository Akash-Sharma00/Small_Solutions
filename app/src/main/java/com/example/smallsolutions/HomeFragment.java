package com.example.smallsolutions;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements View.OnClickListener {

    // getting fields
    RelativeLayout carpenter, plumber, electrician, appDev, cook, allPro;

    //        Getting search bar
    TextView more;
    ImageView searchIcon;
    AutoCompleteTextView profession;
    String[] searchBarArray = {"Carpenter", "Electrician", "Mechanic", "Plumber", "Web Developer", "App Developer", "Photo Editor", "Video Editor", "Digital Marketer", "Cook"};

    String professionString = "";

    RecyclerView HomeRecycler;
    ArrayList<UserDetails> HomeHolder;
    Home_Recycler_Adapter adapter;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_home, container, false);



//        Creating all hooks
         carpenter = myView.findViewById(R.id.carpenter);
         plumber = myView.findViewById(R.id.plumber);
         electrician = myView.findViewById(R.id.electrician);
         appDev = myView.findViewById(R.id.appdevloper);
         cook = myView.findViewById(R.id.cook2);
         allPro = myView.findViewById(R.id.allpro);
         searchIcon = myView.findViewById(R.id.searchPro);
        profession = myView.findViewById(R.id.enteredPro);
        more = myView.findViewById(R.id.more);

        HomeHolder = new ArrayList<>();
        HomeRecycler = myView.findViewById(R.id.homeRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        HomeRecycler.setLayoutManager(linearLayoutManager);
        HomeRecycler.setHasFixedSize(true);


//         Setting Clicks on every clickable object
         carpenter.setOnClickListener(this);
         plumber.setOnClickListener(this);
         electrician.setOnClickListener(this);
         appDev.setOnClickListener(this);
         cook.setOnClickListener(this);
         allPro.setOnClickListener(this);
        searchIcon.setOnClickListener(this);
        more.setOnClickListener(this);

//        Function call for searchbar
        searchAdapter(myView);

        addDataInRecycler(myView);


        return myView;
    }

    private void addDataInRecycler(View myView) {
        ProgressBar progress;
        progress = myView.findViewById(R.id.homeProgress);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users/profession");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    for (DataSnapshot data: dataSnapshot.getChildren()){
                        UserDetails userDetails = data.getValue(UserDetails.class);
                        HomeHolder.add(userDetails);
                    }
                    progress.setVisibility(View.GONE);
                    HomeRecycler.setAdapter(new Home_Recycler_Adapter(getActivity(), HomeHolder));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void searchAdapter(View myView) {

        ArrayAdapter<String> adapterList;
        adapterList = new ArrayAdapter(myView.getContext(), R.layout.dropdown_textview, R.id.items_design, searchBarArray);

        profession.setAdapter(adapterList);

        profession.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 professionString = adapterList.getItem(i);
            }
        });

    }

    @Override
    public void onClick(View v) {
        EditText searchTxt;
        switch (v.getId()){
            case R.id.carpenter:
                Intent carp = new Intent(getActivity(), AllRandom.class);
                carp.putExtra("pro","Carpenter");
                carp.putExtra("toolbarTitle","Carpenter");
                startActivity(carp);
                getActivity().finish();
                break;

            case R.id.plumber:
                Intent plum = new Intent(getActivity(), AllRandom.class);
                plum.putExtra("pro","Plumber");
                plum.putExtra("toolbarTitle","Plumber");
                startActivity(plum);
                getActivity().finish();
                break;

            case R.id.electrician:
                Intent elect = new Intent(getActivity(), AllRandom.class);
                elect.putExtra("pro","Electrician");
                elect.putExtra("toolbarTitle","Electrician");
                startActivity(elect);
                getActivity().finish();
                break;

            case R.id.appdevloper:
                Intent app = new Intent(getActivity(), AllRandom.class);
                app.putExtra("pro","App Developer");
                app.putExtra("toolbarTitle","App Developer");
                startActivity(app);
                getActivity().finish();
                break;

            case R.id.cook2:
                Intent cook = new Intent(getActivity(), AllRandom.class);
                cook.putExtra("pro","Cook");
                cook.putExtra("toolbarTitle","Cook");
                startActivity(cook);
                getActivity().finish();
                break;

            case R.id.allpro:
            case R.id.more:
                Intent more1 = new Intent(getActivity(), AllRandom.class);
                more1.putExtra("pro","more");
                more1.putExtra("toolbarTitle","All Category");
                startActivity(more1);
                break;
            case R.id.searchPro:
                searchProfession();
                break;
        }
    }

//  Function to show suggestion in searchbar
    private void searchProfession() {

        String Profession = profession.getText().toString();

        if(Profession.isEmpty()){
            Toast.makeText(getContext(), "Search Can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

//                 If users search for two word profession like app developer
        if (Profession.contains(" ")){
            int index = Profession.indexOf(" ") + 1;
            String first = Profession.substring(0,1).toUpperCase() + Profession.substring(1,index);
            String second = Profession.substring(index, index+1).toUpperCase() + Profession.substring(index+1);
            String result = first + second;
            Intent search = new Intent(getActivity(), AllRandom.class);
            search.putExtra("pro", result);
            startActivity(search);
        }

        //Single word profession
        else {

            String result = Profession.substring(0,1).toUpperCase()+Profession.substring(1);
            Intent search = new Intent(getActivity(), AllRandom.class);
            search.putExtra("pro", result);
            startActivity(search);
        }
        getActivity().finish();

    }
}