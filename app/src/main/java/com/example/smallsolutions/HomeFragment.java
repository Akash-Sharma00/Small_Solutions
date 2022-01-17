package com.example.smallsolutions;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HomeFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_home, container, false);

        RelativeLayout carpenter, plumber, electrician, appDev, cook, allPro; // getting fields

//        Creating all hooks
         carpenter = myView.findViewById(R.id.carpenter);
         plumber = myView.findViewById(R.id.plumber);
         electrician = myView.findViewById(R.id.electrician);
         appDev = myView.findViewById(R.id.appdevloper);
         cook = myView.findViewById(R.id.cook);
         allPro = myView.findViewById(R.id.allpro);

         carpenter.setOnClickListener(this);
         plumber.setOnClickListener(this);
         electrician.setOnClickListener(this);
         appDev.setOnClickListener(this);
         cook.setOnClickListener(this);
         allPro.setOnClickListener(this);

//        Calling all profession
        TextView more = myView.findViewById(R.id.more);
        more.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.more:
//                calling all random activity
                Intent more = new Intent(getActivity(), AllRandom.class);
                more.putExtra("pro","more");
                startActivity(more);
                break;
            case R.id.carpenter:
                Toast.makeText(getContext(), "C", Toast.LENGTH_SHORT).show();
                Intent carp = new Intent(getActivity(), AllRandom.class);
                carp.putExtra("pro","Carpenter");
                startActivity(carp);
                break;
            case R.id.plumber:
                Toast.makeText(getContext(), "P", Toast.LENGTH_SHORT).show();
                Intent plum = new Intent(getActivity(), AllRandom.class);
                plum.putExtra("pro","Plumber");
                startActivity(plum);
                break;
            case R.id.electrician:
                Toast.makeText(getContext(), "E", Toast.LENGTH_SHORT).show();
                Intent elect = new Intent(getActivity(), AllRandom.class);
                elect.putExtra("pro","Electrician");
                startActivity(elect);
                break;
            case R.id.appdevloper:
                Toast.makeText(getContext(), "A", Toast.LENGTH_SHORT).show();
                Intent app = new Intent(getActivity(), AllRandom.class);
                app.putExtra("pro","App Developer");
                startActivity(app);
                break;
            case R.id.cook:
                Toast.makeText(getContext(), "Co", Toast.LENGTH_SHORT).show();
                Intent cook = new Intent(getActivity(), AllRandom.class);
                cook.putExtra("pro","Cook");
                startActivity(cook);
                break;
            case R.id.allpro:
                Toast.makeText(getContext(), "ALL", Toast.LENGTH_SHORT).show();
                Intent more1 = new Intent(getActivity(), AllRandom.class);
                more1.putExtra("pro","more");
                startActivity(more1);
                break;
        }
    }
}