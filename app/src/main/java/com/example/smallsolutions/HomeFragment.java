package com.example.smallsolutions;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HomeFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_home, container, false);

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
//                Toast.makeText(getActivity(), "More called", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(),AllRandom.class);
                v.getContext().startActivity(intent);
                break;
        }
    }
}