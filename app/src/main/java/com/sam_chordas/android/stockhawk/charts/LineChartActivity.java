package com.sam_chordas.android.stockhawk.charts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.RetrofitContract.QuotesEndPoint;
import com.sam_chordas.android.stockhawk.model.Endpoint;
import com.sam_chordas.android.stockhawk.model.Quote;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import com.example.sam_chordas.stockhawk.R;

public class LineChartActivity extends AppCompatActivity {

     LineChart lineChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
//        getActionBar().setDisplayHomeAsUpEnabled(true);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lineChart = (LineChart) findViewById(R.id.line_chart);
//        showToastMessage(getIntent().getStringExtra("symbol"));
//        setupBarChart();

        getQuotesEndpoint(getIntent().getStringExtra("symbol"));

    }


    void showToastMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


    void setupBarChart(ArrayList<Entry> entries)
    {
        /*
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(8f, 1));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(12f, 3));
        entries.add(new Entry(18f, 4));
        entries.add(new Entry(9f, 5));
        */


        LineDataSet dataset = new LineDataSet(entries, "# of Calls");
        dataset.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        dataSets.add(dataset);



        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> labels = new ArrayList<String>();


//        labels.add("January");
//        labels.add("February");
//        labels.add("March");
//        labels.add("April");
//        labels.add("May");
//        labels.add("June");

        for(Entry entry : entries)
        {
            labels.add(String.valueOf(entry.getVal()));
        }


        LineData data = new LineData(labels,dataSets);



        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1.Q"); xVals.add("2.Q"); xVals.add("3.Q"); xVals.add("4.Q");

        lineChart.setData(data);
        lineChart.invalidate();
    }



    public void getQuotesEndpoint(String symbol)
    {
         // &q=select * from yahoo.finance.historicaldata where symbol = "YHOO" and startDate = "2016-01-01" and endDate = "2016-03-01"


//        showToastMessage("Network call made !");

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://query.yahooapis.com")
                .build();


        QuotesEndPoint quotesEndPoint = retrofit.create(QuotesEndPoint.class);

        final String query = "select * from yahoo.finance.historicaldata where symbol = \"" + symbol + "\" and startDate = " + "\"2016-01-01\"" + " and endDate = " + "\"2016-01-15\"";

        Call<Endpoint> endpointCall = quotesEndPoint.getQuotesEndPoint(query);

        Log.d("networklog",query);

        endpointCall.enqueue(new Callback<Endpoint>() {
            @Override
            public void onResponse(Call<Endpoint> call, Response<Endpoint> response) {

                Endpoint endpoint = response.body();

                Log.d("networklog",response.toString());


//                showToastMessage("success");

//                showToastMessage("Success");

                if(endpoint==null)
                {
                    showToastMessage("endpoint null");

                    return;
                }

                ArrayList<Quote> quotes = endpoint.getQuery().getResults().getQuote();

                int i = 0;

                ArrayList<Entry> entries = new ArrayList<>();

                for(Quote quote : quotes)
                {


                    Log.d("networklog",

                            quote.getSymbol()
                            + " "
                            + String.valueOf(quote.getLow())
                            + " : "
                            + String.valueOf(quote.getHigh())
                    );

                    entries.add(new Entry((float)Double.parseDouble(quote.getLow()), i));

                    i = i + 1;

                }

                setupBarChart(entries);
            }

            @Override
            public void onFailure(Call<Endpoint> call, Throwable t) {

                showToastMessage("Network Request Failed !");
            }
        });
    }

}
