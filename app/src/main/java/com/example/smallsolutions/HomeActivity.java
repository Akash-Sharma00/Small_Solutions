package com.example.smallsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ListView listView1;
    String[] ListElement = new String[]{
            "CARPENTER 1",
            "CARPENTER 2",
            "CARPENTER 3",
            "CARPENTER 4",
            "CARPENTER 5",
            "CARPENTER 6",
            "CARPENTER 7",
            "CARPENTER 8",
            "CARPENTER 9",
            "CARPENTER 10"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView carpenter = (TextView) findViewById(R.id.carpenter);
        carpenter.setBackgroundResource(R.drawable.blue);
        listView1 = findViewById(R.id.carplist);
        List<String> ListElementArrayList = new ArrayList<>(Arrays.asList(ListElement));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListElementArrayList);
        listView1.setAdapter(adapter);
    }
}