package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

class LoginFragmentAdapter extends FragmentStateAdapter {

    public LoginFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new SignupFragment();
        }
        return new LoginFragment();
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
