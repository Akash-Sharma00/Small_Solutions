package com.example.smallsolutions;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class HomeFragment extends Fragment implements View.OnClickListener {

    // getting fields
    RelativeLayout carpenter, plumber, electrician, appDev, cook, allPro;

    //        Getting search bar
    ImageView searchIcon;
    EditText profession;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_home, container, false);


//        Creating all hooks
         carpenter = myView.findViewById(R.id.carpenter);
         plumber = myView.findViewById(R.id.plumber);
         electrician = myView.findViewById(R.id.electrician);
         appDev = myView.findViewById(R.id.appdevloper);
         cook = myView.findViewById(R.id.cook);
         allPro = myView.findViewById(R.id.allpro);
         searchIcon = myView.findViewById(R.id.searchPro);
        TextView more = myView.findViewById(R.id.more);
        profession = myView.findViewById(R.id.enteredPro);

//         Setting Clicks on every clickable object
         carpenter.setOnClickListener(this);
         plumber.setOnClickListener(this);
         electrician.setOnClickListener(this);
         appDev.setOnClickListener(this);
         cook.setOnClickListener(this);
         allPro.setOnClickListener(this);
        more.setOnClickListener(this);
        searchIcon.setOnClickListener(this);

        return myView;
    }

    @Override
    public void onClick(View v) {
        EditText searchTxt;
        switch (v.getId()){
            case R.id.more:
//                calling all random activity
                Intent more = new Intent(getActivity(), AllRandom.class);
                more.putExtra("pro","more");
                startActivity(more);
                getActivity().finish();
                break;

            case R.id.carpenter:
                Toast.makeText(getContext(), "C", Toast.LENGTH_SHORT).show();
                Intent carp = new Intent(getActivity(), AllRandom.class);
                carp.putExtra("pro","Carpenter");
                startActivity(carp);
                getActivity().finish();
                break;

            case R.id.plumber:
                Toast.makeText(getContext(), "P", Toast.LENGTH_SHORT).show();
                Intent plum = new Intent(getActivity(), AllRandom.class);
                plum.putExtra("pro","Plumber");
                startActivity(plum);
                getActivity().finish();
                break;

            case R.id.electrician:
                Toast.makeText(getContext(), "E", Toast.LENGTH_SHORT).show();
                Intent elect = new Intent(getActivity(), AllRandom.class);
                elect.putExtra("pro","Electrician");
                startActivity(elect);
                getActivity().finish();
                break;

            case R.id.appdevloper:
                Toast.makeText(getContext(), "A", Toast.LENGTH_SHORT).show();
                Intent app = new Intent(getActivity(), AllRandom.class);
                app.putExtra("pro","App Developer");
                startActivity(app);
                getActivity().finish();
                break;

            case R.id.cook:
                Toast.makeText(getContext(), "Co", Toast.LENGTH_SHORT).show();
                Intent cook = new Intent(getActivity(), AllRandom.class);
                cook.putExtra("pro","Cook");
                startActivity(cook);
                getActivity().finish();
                break;

            case R.id.allpro:
                Toast.makeText(getContext(), "ALL", Toast.LENGTH_SHORT).show();
                Intent more1 = new Intent(getActivity(), AllRandom.class);
                more1.putExtra("pro","more");
                startActivity(more1);
                break;
            case R.id.searchPro:
                searchProfession();
        }
    }




    private void searchProfession() {
        String Profession = profession.getText().toString();

//                 If users search for two word profession like app developer
        if (Profession.contains(" ")){
            int index = Profession.indexOf(" ") + 1;
            String first = Profession.substring(0,1).toUpperCase() + Profession.substring(1,index);
            String second = Profession.substring(index, index+1).toUpperCase() + Profession.substring(index+1);
            String result = first + second;
            Intent search = new Intent(getActivity(), AllRandom.class);
            search.putExtra("pro", result);
            startActivity(search);
            Toast.makeText(getActivity(),result, Toast.LENGTH_SHORT).show();
        }

        //Single word profession
        else {

            String result = Profession.substring(0,1).toUpperCase()+Profession.substring(1);
            Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
            Intent search = new Intent(getActivity(), AllRandom.class);
            search.putExtra("pro", result);
            startActivity(search);
        }
        getActivity().finish();

    }
}