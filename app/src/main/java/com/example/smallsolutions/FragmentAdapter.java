package com.example.smallsolutions;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 1:
                return new CategoryFragment();
            case 2:
                return new CallHistory();
            case 3:
                return new LocalUserProfileFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
