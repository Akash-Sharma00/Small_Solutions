package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

//     Creating variable for viewpager, tablayout and adapter class
    ViewPager2 pager2;
    TabLayout tabLayout;
    LoginFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

//        For welcome text animation
        NestedScrollView scrollView = findViewById(R.id.form);

        TextView welcome_text = findViewById(R.id.welcome_text_toolbar);
        welcome_text.animate().translationY(scrollView.getScrollY()).setDuration(0);

//        Hooks
        pager2 = findViewById(R.id.form_viewpager);
        tabLayout = findViewById(R.id.form_tab);
        adapter = new LoginFragmentAdapter(getSupportFragmentManager(), getLifecycle());

//        Setting adapter for viewpager
        pager2.setAdapter(adapter);

//        Creating Tabs
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign up"));

        tabLayout.setSelectedTabIndicatorColor(Color.rgb(48,99,184));

//        Hooking tablayout with viewpager
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

//        Collapse listener for appbar
        AppBarLayout appBarLayout;
        appBarLayout = findViewById(R.id.appbar_layout);

//        function to listen collapsing of appbar AppBarLayout appBarLayout;

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0){
                    NestedScrollView scrollView;
                    scrollView = findViewById(R.id.form);
                    scrollView.setBackgroundColor(Color.rgb(255,255,255));

                    CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
                    collapsingToolbarLayout.setContentScrimResource(R.drawable.toolbar_background);
                }
                else{
                    NestedScrollView scrollView;
                    scrollView = findViewById(R.id.form);
                    scrollView.setBackgroundResource(R.drawable.form_background);
                }
            }
        });

//        Funtion to create animation
        animation();
    }

    public void animation(){
        tabLayout.setAlpha(0);
        tabLayout.setTranslationX(300);
        tabLayout.animate().translationX(0).alpha(1).setDuration(800);
    }
}