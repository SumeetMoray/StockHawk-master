package com.sam_chordas.android.stockhawk.RetrofitContract;

import com.sam_chordas.android.stockhawk.model.Endpoint;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sumeet on 23/7/16.
 */
public interface QuotesEndPoint {


    // base URL : https://query.yahooapis.com

    // &q=select * from yahoo.finance.historicaldata where symbol = "YHOO" and startDate = "2016-01-01" and endDate = "2016-03-01"

    @GET("/v1/public/yql?&format=json&diagnostics=false&env=store://datatables.org/alltableswithkeys")
    Call<Endpoint> getQuotesEndPoint(@Query("q")String query);


}
