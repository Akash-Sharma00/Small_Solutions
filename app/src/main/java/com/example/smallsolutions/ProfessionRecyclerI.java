package com.example.smallsolutions;

import android.widget.ArrayAdapter;

class ProfessionRecyclerI {
    private ArrayAdapter<String> professionList;

    public ProfessionRecyclerI(ArrayAdapter<String> professionList) {
        this.professionList = professionList;
    }

    public ArrayAdapter<String> getAdapter(){return professionList;}
}
