package com.example.smallsolutions;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;


public class HomeFragment extends Fragment implements OnClickListener{

    private DrawerLayout mDrawer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView hamburger = myView.findViewById(R.id.burger);
        hamburger.setOnClickListener(this);

        mDrawer = getActivity().findViewById(R.id.drawer_layout);


        return myView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.burger){
            mDrawer.openDrawer(GravityCompat.END);
        }
    }


}