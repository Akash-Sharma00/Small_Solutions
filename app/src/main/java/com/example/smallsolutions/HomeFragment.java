package com.example.smallsolutions;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;


public class HomeFragment extends Fragment implements OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout mDrawer;
    Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView hamburger = myView.findViewById(R.id.burger);
        hamburger.setOnClickListener(this);

        toolbar = myView.findViewById(R.id.toolbar);
        mDrawer = getActivity().findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawer, R.string.open_drawer, R.string.close_drawer);

        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        NavigationView navigationView = myView.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.burger){
            mDrawer.openDrawer(GravityCompat.END);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.first)
        {
            Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}