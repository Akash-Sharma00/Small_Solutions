package com.example.smallsolutions;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CategoryFragment extends Fragment implements View.OnClickListener {

    CardView carpenter, electrician, mechanic, plumber, webDeveloper, appDeveloper, photoEditor, videoEditor, digitalMarketer, cook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView =  inflater.inflate(R.layout.fragment_category, container, false);

//        Getting all hooks

        carpenter = myView.findViewById(R.id.carpenter2);
        electrician = myView.findViewById(R.id.electrician2);
        mechanic = myView.findViewById(R.id.mechanic2);
        plumber = myView.findViewById(R.id.plumber2);
        webDeveloper = myView.findViewById(R.id.webdeveloper2);
        appDeveloper = myView.findViewById(R.id.appdeveloper2);
        photoEditor = myView.findViewById(R.id.photoeditor2);
        videoEditor = myView.findViewById(R.id.videoeditor2);
        digitalMarketer = myView.findViewById(R.id.DigitalMarketer2);
        cook = myView.findViewById(R.id.cook2);

//        Setting Click listeners

        carpenter.setOnClickListener(this);
        electrician.setOnClickListener(this);
        mechanic.setOnClickListener(this);
        plumber.setOnClickListener(this);
        webDeveloper.setOnClickListener(this);
        appDeveloper.setOnClickListener(this);
        photoEditor.setOnClickListener(this);
        videoEditor.setOnClickListener(this);
        digitalMarketer.setOnClickListener(this);
        cook.setOnClickListener(this);

        return myView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.carpenter2:
                carpenter.setCardBackgroundColor(R.drawable.blue);
                Intent car = new Intent(getActivity(), AllRandom.class);
                car.putExtra("pro","Carpenter");
                car.putExtra("toolbarTitle","Carpenter");
                startActivity(car);
                getActivity().finish();
                break;

            case R.id.electrician2:
                electrician.setCardBackgroundColor(R.drawable.blue);
                Intent elect = new Intent(getActivity(), AllRandom.class);
                elect.putExtra("pro","Electrician");
                elect.putExtra("toolbarTitle","Electrician");
                startActivity(elect);
                getActivity().finish();
                break;

            case R.id.mechanic2:
                mechanic.setCardBackgroundColor(R.drawable.blue);
                Intent mech = new Intent(getActivity(), AllRandom.class);
                mech.putExtra("pro","Mechanic");
                mech.putExtra("toolbarTitle","Mechanic");
                startActivity(mech);
                getActivity().finish();
                break;

            case R.id.plumber2:
                plumber.setCardBackgroundColor(R.drawable.blue);
                Intent plum = new Intent(getActivity(), AllRandom.class);
                plum.putExtra("pro","Plumber");
                plum.putExtra("toolbarTitle","Plumber");
                startActivity(plum);
                getActivity().finish();
                break;

            case R.id.webdeveloper2:
                webDeveloper.setCardBackgroundColor(R.drawable.blue);
                Intent web = new Intent(getActivity(), AllRandom.class);
                web.putExtra("pro","Web Developer");
                web.putExtra("toolbarTitle","Web Developer");
                startActivity(web);
                getActivity().finish();
                break;

            case R.id.appdeveloper2:
                appDeveloper.setCardBackgroundColor(R.drawable.blue);
                Intent app = new Intent(getActivity(), AllRandom.class);
                app.putExtra("pro","App Developer");
                app.putExtra("toolbarTitle","App Developer");
                startActivity(app);
                getActivity().finish();
                break;

            case R.id.photoeditor2:
                photoEditor.setCardBackgroundColor(R.drawable.blue);
                Intent photo = new Intent(getActivity(), AllRandom.class);
                photo.putExtra("pro","Photo Editor");
                photo.putExtra("toolbarTitle","Photo Editor");
                startActivity(photo);
                getActivity().finish();
                break;

            case R.id.videoeditor2:
                videoEditor.setCardBackgroundColor(R.drawable.blue);
                Intent video = new Intent(getActivity(), AllRandom.class);
                video.putExtra("pro","Video Editor");
                video.putExtra("toolbarTitle","Video Editor");
                startActivity(video);
                getActivity().finish();
                break;

            case R.id.DigitalMarketer2:
                digitalMarketer.setCardBackgroundColor(R.drawable.blue);
                Intent digital = new Intent(getActivity(), AllRandom.class);
                digital.putExtra("pro","Digital Marketer");
                digital.putExtra("toolbarTitle","Digital Marketer");
                startActivity(digital);
                getActivity().finish();
                break;

            case R.id.cook2:
                cook.setCardBackgroundColor(R.drawable.blue);
                Intent cook = new Intent(getActivity(), AllRandom.class);
                cook.putExtra("pro","Cook");
                cook.putExtra("toolbarTitle","Cook");
                startActivity(cook);
                getActivity().finish();
                break;
        }
    }
}