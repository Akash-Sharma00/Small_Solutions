package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    Variables for viewpager and tabLayout
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentAdapter fragmentAdapter;
    TextView navUser;
    ImageView navImage, chat;

    FirebaseAuth Auth;
    FirebaseDatabase database;
    DatabaseReference reference;

//    Variable for toolbar
    Toolbar toolbar;

//    Drawer Variable
    private DrawerLayout drawer;

//    Variable to track viewPager's current position

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        Hooks for tabLayout and viewpager
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewpager2);

//        Creating adapter
        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(fm, getLifecycle());
        viewPager2.setAdapter(fragmentAdapter);

//        Hooking tabLayout with viewpager
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().clearColorFilter();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

//        Hook for toolbar
        toolbar = findViewById(R.id.toolbar);

//        Setting toolbar as actionbar
        setSupportActionBar(toolbar);
        chat = findViewById(R.id.open_chat);

//        Hook for drawer variable
        drawer = findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        Variable for navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();

        View headerView = navigationView.getHeaderView(0);
        navUser = headerView.findViewById(R.id.NavId);
        navImage = headerView.findViewById(R.id.navImg);

//        Function call for header
        setNavHeader();

        navigationView.setNavigationItemSelectedListener(this);

        chat.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, ChatActivity.class)));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                SigningOut();
                break;
            case R.id.home:
                viewPager2.setCurrentItem(0);
                break;
            case R.id.call_log:
                viewPager2.setCurrentItem(2);
                break;
            case R.id.my_profile:
                viewPager2.setCurrentItem(3);
                break;
            case R.id.chat:
                startActivity(new Intent(getApplicationContext(), ChatActivity.class));
                break;
            case R.id.more_catagory:
                viewPager2.setCurrentItem(1);
                break;
            case R.id.aboutUs:
                startActivity(new Intent(getApplicationContext(), About_Us_Activity.class));
                break;
        }
        drawer.closeDrawer((GravityCompat.START));
        return true;
    }

    private void SigningOut() {
//         Alert Box to confirmation
        Auth = FirebaseAuth.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setCancelable(false);
        builder.setMessage("Are sure, You want to Sigh Out");
        builder.create();
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Auth.signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                             Close
            }
        });
        builder.create().show();
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (viewPager2.getCurrentItem() > 0){
            viewPager2.setCurrentItem(0);
        }
        else{
            super.onBackPressed();
        }
    }


 //       Function for Setting email & image in header

    private void setNavHeader() {

//        Getting Path of user profile

        database = FirebaseDatabase.getInstance();
        Auth = FirebaseAuth.getInstance();

        reference = database.getReference("users/allUsers/" + Auth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String path = snapshot.getValue(String.class);

                reference = database.getReference(path);

//                Getting Email and image url
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String mail = snapshot.child("userEmail").getValue(String.class);
                            String imageUrl = snapshot.child("imageURL").getValue(String.class);

//                            Setting email & image in header
                            navUser.setText(mail);
                        Picasso.get().load(imageUrl).into(navImage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}