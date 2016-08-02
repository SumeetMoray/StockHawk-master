package com.sam_chordas.android.stockhawk.charts;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.sam_chordas.android.stockhawk.R;


public class graphChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph_chart);
        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
