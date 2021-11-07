package com.example.smallsolutions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class ProfessionAdapter extends RecyclerView.Adapter<ProfessionAdapter.ViewHolder> {

    ArrayList<ProfessionRecyclerI> itemList;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        AutoCompleteTextView autoCompleteTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            autoCompleteTextView = itemView.findViewById(R.id.autoComplete_profession);
        }
    }

    public ProfessionAdapter(ArrayList<ProfessionRecyclerI> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.profession_design,parent,false);
        return new ViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfessionRecyclerI currentItem = itemList.get(position);
        holder.autoCompleteTextView.setAdapter(currentItem.getAdapter());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
